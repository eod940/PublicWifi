package org.publicwifi.v1.servlet.wifi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.publicwifi.v1.dao.PublicWifiDAO;
import org.publicwifi.v1.dto.PublicWifiDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/load-wifi")
public class GetWifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/WEB-INF/views/load-wifi.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        int dbCount = 0;  // 개수

        // DB에 저장하는 코드
        try {
            dbCount = getAllApi();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("서블릿 정상 작동");  // 로그
        System.out.println("dbCount: " + dbCount);

        // 완료 후 forward
        request.setAttribute("nums", dbCount);
        requestDispatcher.forward(request, response);

    }

    public static String urlBulid(int startPage, int lastPage){
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        String key = "546e4d5350656f6433385453487941";
        String type = "json";
        String info = "TbPublicWifiInfo";
        String startIdx = Integer.toString(startPage);
        String endIdx = Integer.toString(lastPage);

        urlBuilder.append("/").append(key);
        urlBuilder.append("/").append(type);
        urlBuilder.append("/").append(info);
        urlBuilder.append("/").append(startIdx);
        urlBuilder.append("/").append(endIdx);

        return urlBuilder.toString();
    }

    public static int getAllApi() throws IOException, SQLException, ClassNotFoundException {

        PublicWifiDAO publicWifiDAO = new PublicWifiDAO();
        int cnt = 0;

        // 요청받을 url 만들기
        String url = urlBulid(1, 1);

        // OkHttp 클라이언트 객체 생성
        OkHttpClient client = new OkHttpClient();

        // GET 요청 객체 생성
        Request.Builder builder = new Request.Builder().url(url).get();
        Request request = builder.build();

        // OkHttp 클라이언트로 GET 요청 객체 전송
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            // 응답 받아서 처리
            ResponseBody body = response.body();
            if (body != null) {
                String bodyBuild = body.string();
                System.out.println("Response:" + bodyBuild);

                // Json 문자열 -> JsonObject, JsonArray
                JsonArray wifi_info;
                int list_total_count = JsonParser.parseString(bodyBuild)
                        .getAsJsonObject()
                        .get("TbPublicWifiInfo")
                        .getAsJsonObject()
                        .get("list_total_count")
                        .getAsInt();

                boolean isWorking = true;
                int startNum = 1;
                int lastNum = startNum + 999;

                while (isWorking) {
                    url = urlBulid(startNum, lastNum);
                    builder = new Request.Builder().url(url).get();
                    request = builder.build();
                    response = client.newCall(request).execute();

                    // 응답 받아서 처리
                    body = response.body();

                    assert body != null;
                    bodyBuild = body.string();

                    // Json 문자열 -> JsonObject, JsonArray
                    wifi_info = JsonParser.parseString(bodyBuild)
                            .getAsJsonObject()
                            .get("TbPublicWifiInfo")
                            .getAsJsonObject()
                            .get("row")
                            .getAsJsonArray();

                    for (int i = 0; i < wifi_info.size(); i++) {
                        JsonObject object = wifi_info.get(i).getAsJsonObject();
                        PublicWifiDTO publicWifiDTO = new PublicWifiDTO(
                                object.get("X_SWIFI_MGR_NO").getAsString(),
                                object.get("X_SWIFI_WRDOFC").getAsString(),
                                object.get("X_SWIFI_MAIN_NM").getAsString(),
                                object.get("X_SWIFI_ADRES1").getAsString(),
                                object.get("X_SWIFI_ADRES2").getAsString(),
                                object.get("X_SWIFI_INSTL_FLOOR").getAsString(),
                                object.get("X_SWIFI_INSTL_TY").getAsString(),
                                object.get("X_SWIFI_INSTL_MBY").getAsString(),
                                object.get("X_SWIFI_SVC_SE").getAsString(),
                                object.get("X_SWIFI_CMCWR").getAsString(),
                                object.get("X_SWIFI_CNSTC_YEAR").getAsString(),
                                object.get("X_SWIFI_INOUT_DOOR").getAsString(),
                                object.get("X_SWIFI_REMARS3").getAsString(),
                                object.get("LAT").getAsDouble(),
                                object.get("LNT").getAsDouble(),
                                object.get("WORK_DTTM").getAsString()
                        );

                        cnt += publicWifiDAO.insertPublicWifi(publicWifiDTO);

                    }

                    if (lastNum == list_total_count) {
                        isWorking = false;
                    }

                    startNum = lastNum + 1;
                    lastNum = startNum + 999;
                    if (lastNum > list_total_count)
                        lastNum = list_total_count;

                }
            } else {
                System.err.println("Error Occurred");
            }
        }
        return cnt;
    }
}
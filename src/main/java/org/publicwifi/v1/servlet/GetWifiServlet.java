package org.publicwifi.v1.servlet;

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

@WebServlet("/load-wifi")
public class GetWifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/WEB-INF/views/load-wifi.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);

        // DB에 저장하는 코드
//        try {
//            getAllApi();
//            PublicWifiDAO publicWifiDAO = new PublicWifiDAO();
//            publicWifiDAO.insertPublicWifi();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("서블릿 정상 작동");

        int dbCode = (int) (Math.random() * 2);  // 종료 코드 (1: 정상, 0: 에러)
        System.out.println(dbCode);

        // 완료 후 redirect
        if (dbCode == 1) {
            request.setAttribute("nums", dbCode);
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("error", dbCode);
            requestDispatcher.forward(request, response);
        }
    }

    public String urlBulid(int startPage, int lastPage){
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

    public int getAllApi() throws IOException {

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

                    }

                    if (lastNum == list_total_count) {
                        isWorking = false;
                    }

                    startNum = lastNum;
                    lastNum = startNum + 999;
                    if (lastNum > list_total_count)
                        lastNum = list_total_count;

                    System.out.println("lastNum = " + lastNum);
                    System.out.println("list_total_count = " + list_total_count);

                }
                return getTotalCount(lastNum, list_total_count);
            } else {
                System.err.println("Error Occurred");
            }
        }
        return -1;
    }

    private int getTotalCount(int lastNum, int listTotalCount) {
        if (lastNum == listTotalCount) {
            return listTotalCount;
        } else {
            return -1;
        }
    }
}
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;
import org.publicwifi.v1.dao.PublicWifiDAO;
import org.publicwifi.v1.dto.PublicWifiDTO;

import java.io.IOException;
import java.sql.*;

public class InsertTest {


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

    @Test
    public void getAllApiTest() throws IOException, ClassNotFoundException {

        PublicWifiDAO publicWifiDAO = new PublicWifiDAO();

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
                    System.out.println("startNum = " + startNum);
                    System.out.println("lastNum = " + lastNum);
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

                        insertPublicWifiTest(publicWifiDTO);

                    }

                    if (lastNum == list_total_count) {
                        isWorking = false;
                    }

                    startNum = lastNum + 1;
                    lastNum = startNum + 999;

                    if (lastNum > list_total_count)
                        lastNum = list_total_count;

                }
                System.out.println(getTotalCountTest(lastNum, list_total_count));
            } else {
                System.err.println("Error Occurred");
            }
        }
    }

    private int getTotalCountTest(int lastNum, int listTotalCount) {
        if (lastNum == listTotalCount) {
            return listTotalCount;
        } else {
            return -1;
        }
    }

    public void insertPublicWifiTest(PublicWifiDTO dto) throws ClassNotFoundException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:./publicWifi.sqlite3");
            Statement statement = connection.createStatement();
            PreparedStatement psmt = connection.prepareStatement("insert into public_wifi values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            psmt.setString(1, dto.getX_SWIFI_MGR_NO());
            psmt.setString(2, dto.getX_SWIFI_WRDOFC());
            psmt.setString(3, dto.getX_SWIFI_MAIN_NM());
            psmt.setString(4, dto.getX_SWIFI_ADRES1());
            psmt.setString(5, dto.getX_SWIFI_ADRES2());
            psmt.setString(6, dto.getX_SWIFI_INSTL_FLOOR());
            psmt.setString(7, dto.getX_SWIFI_INSTL_TY());
            psmt.setString(8, dto.getX_SWIFI_INSTL_MBY());
            psmt.setString(9, dto.getX_SWIFI_SVC_SE());
            psmt.setString(10, dto.getX_SWIFI_CMCWR());
            psmt.setString(11, dto.getX_SWIFI_CNSTC_YEAR());
            psmt.setString(12, dto.getX_SWIFI_INOUT_DOOR());
            psmt.setString(13, dto.getX_SWIFI_REMARS3());
            psmt.setDouble(14, dto.getLAT());
            psmt.setDouble(15, dto.getLNT());
            psmt.setString(16, dto.getWORK_DTTM());

            psmt.executeUpdate();

//            ResultSet rs = statement.executeQuery("select * from public_wifi");
//            System.out.println(rs.next());
            // read the result set
//            System.out.println("id = " + rs.getString("X_SWIFI_MGR_NO"));

            statement.executeUpdate("delete from public_wifi");
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}

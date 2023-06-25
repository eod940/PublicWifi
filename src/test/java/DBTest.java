
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

public class DBTest {

    @Test
    public void sqliteTest() throws ClassNotFoundException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/leo/github/Project/PublicWifi/publicWifi.sqlite3");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select * from person");
            while(rs.next())
            {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
            statement.executeUpdate("drop table person");
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
    public void pagingTest() throws IOException {
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
                JsonArray wifi_info = JsonParser.parseString(bodyBuild)
                        .getAsJsonObject()
                        .get("TbPublicWifiInfo")
                        .getAsJsonObject()
                        .get("row")
                        .getAsJsonArray();

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
                    wifi_info = JsonParser.parseString(bodyBuild)
                            .getAsJsonObject()
                            .get("TbPublicWifiInfo")
                            .getAsJsonObject()
                            .get("row")
                            .getAsJsonArray();

                    for (int i = 0; i < wifi_info.size(); i++) {
                        JsonObject object = wifi_info.get(i).getAsJsonObject();
                        System.out.println("번호 : " + object.get("X_SWIFI_MGR_NO"));
                        System.out.println("startNum = " + startNum);
                        System.out.println("lastNum = " + lastNum);
                        System.out.println("위치 : " + (lastNum - startNum));
                        System.out.println("wifi.size(): " + wifi_info.size());
                        System.out.println("------------------------");
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
            } else {
                System.err.println("Error Occurred");
            }
        }
    }
}

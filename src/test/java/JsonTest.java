import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jsonTest.Person;
import jsonTest.Wifi;
import okhttp3.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonTest {
    String json;
    Gson gson;

    @Before
    public void setBefore() {
        json = "{\n" +
                "\"name\":\"홍길동\",\n" +
                "\"age\":14,\n" +
                "\"height\":182.4,\n" +
                "\"marriage\":false\n" +
                "}";
        gson = new Gson();
    }

    @Test
    public void gsonTest() {
        Person person = gson.fromJson(json, Person.class);
        System.out.println("이름 : " + person.getName());
        System.out.println("나이 : " + person.getAge());
        System.out.println("키 : " + person.getHeight());
        System.out.println("결혼 여부 : " + person.isMarriage());

        System.out.println("lombok setter 테스트");
        person.setAge(24);
        System.out.println("나이 : " + person.getAge());

    }

    @Test
    public void apiTest() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        String key = "546e4d5350656f6433385453487941";
        String type = "json";
        String info = "TbPublicWifiInfo";
        String startIdx = "1";
        String endIdx = "20";

        urlBuilder.append("/").append(key);
        urlBuilder.append("/").append(type);
        urlBuilder.append("/").append(info);
        urlBuilder.append("/").append(startIdx);
        urlBuilder.append("/").append(endIdx);
        String url = urlBuilder.toString();

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

                System.out.println(wifi_info);


                for (int i = 0; i < wifi_info.size(); i++) {
                    JsonObject object = wifi_info.get(i).getAsJsonObject();
                    System.out.println("번호 : " + object.get("X_SWIFI_MGR_NO"));
                    System.out.println("위치 : " + object.get("X_SWIFI_WRDOFC"));
                    System.out.println("i : " + i);
                    System.out.println("------------------------");
                }
            }
        }
        else
            System.err.println("Error Occurred");
    }
}

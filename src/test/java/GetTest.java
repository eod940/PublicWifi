import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetTest {

    @Test
    public void apiTest() throws IOException {

        String url = "http://localhost:8080/test";
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
            System.out.println(response.request());
            System.out.println("header: " + response.headers());
            if (body != null) {
                String bodyBuild = body.string();
                System.out.println("Response:" + bodyBuild);
            }
        } else
            System.err.println("Error Occurred");
    }
}

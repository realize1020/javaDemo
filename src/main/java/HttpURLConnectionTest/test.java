package HttpURLConnectionTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) throws IOException {
        String fileURL = "https://download.jetbrains.com.cn/idea/ideaIC-2023.1.exe";
        URL url =new URL(fileURL);
        HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        List<String> eTagList = headerFields.get("ETag");
        eTagList.stream().forEach(System.out::println);
        //System.out.println(eTagList.get(0));
    }
}

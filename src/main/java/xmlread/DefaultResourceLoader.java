package xmlread;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getReource(String location) throws Exception {
       if(location == null) throw new Exception("location 不能为null");
       if(location.startsWith(CLASSPATH_URL_PREFIX)){
           return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
       }else{
           try {
               URL url = new URL(location);
               return new UrlResource(url);
           } catch (MalformedURLException e) {
               return new FileSystemResource(location);
           }
       }
    }
}

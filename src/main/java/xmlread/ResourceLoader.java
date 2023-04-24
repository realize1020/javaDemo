package xmlread;

public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getReource(String location) throws Exception;

}

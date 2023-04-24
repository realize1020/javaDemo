package xmlread;

public class test {
    public static void main(String[] args) {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        XmlReader xmlReader = new XmlReader(resourceLoader);
        try {
            xmlReader.loadXml("classpath:spring.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

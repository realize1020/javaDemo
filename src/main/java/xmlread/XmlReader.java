package xmlread;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlReader {

    private ResourceLoader resourceLoader;

    public XmlReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void loadXml(Resource resource) throws Exception {

        try {
            try(InputStream inputStream = resource.getInputStream()){
                doLoadXml(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("IOException parsing XML document from " + resource ,e);
        }
    }



    public void loadXml(Resource... resources) throws Exception{
        for(Resource resource : resources){
            loadXml(resource);
        }
    }

    public void loadXml(String location) throws Exception{
        ResourceLoader resourceLoader = getResourceLoader();
        Resource reource = resourceLoader.getReource(location);
        loadXml(reource);
    }

    private ResourceLoader getResourceLoader() {
        return resourceLoader;
    }


    private void doLoadXml(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for(int i=0;i<childNodes.getLength();i++){
            if(!(childNodes.item(i) instanceof Element)) continue;
            if(!"bean".equals(childNodes.item(i).getNodeName())) continue;

            Element bean = (Element)childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            Class<?> clazz = Class.forName(className);

            //优先级id>name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if(StrUtil.isEmpty(beanName)){
                beanName =StrUtil.lowerFirst(clazz.getSimpleName());
            }

            System.out.println("xml文件里 id ="+id);
            System.out.println("xml文件里 name ="+name);
            System.out.println("xml文件里 className ="+className);
            System.out.println("xml文件里 beanName ="+beanName);


            //在Spring里的话，读取到xml里的bean的有关属性后，就可以组装userDao,userService的beanDefinition了。

        }

    }
}

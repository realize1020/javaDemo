package xmlread2;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

public class test {

    static String  CLASSPATH_URL_PREFIX = "classpath:";

    public static void main(String[] args) throws ClassNotFoundException {
        String location ="classpath:spring.xml";
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            location = location.substring(CLASSPATH_URL_PREFIX.length());
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(location);

        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for(int i=0;i<childNodes.getLength();i++){
            if(childNodes.item(i) instanceof Element){
                if(childNodes.item(i).getNodeName().equals("bean")){
                    Element bean = (Element)childNodes.item(i);
                    String id = bean.getAttribute("id");
                    System.out.println("id:"+id);
                    String name = bean.getAttribute("name");
                    System.out.println("name:"+name);
                    String className = bean.getAttribute("class");
                    System.out.println("class:"+className);

                    String propertyName ="";
                    String propertyValue="";
                    NodeList properties = bean.getChildNodes();
                    for(int j =0;j<properties.getLength();j++){
                        if(!(properties.item(i) instanceof Element)) continue;
                        if (!"property".equals(properties.item(j).getNodeName())) continue;
                        Element property = (Element) properties.item(j);
                        propertyName = property.getAttribute("name");
                        propertyValue = property.getAttribute("value");
                        System.out.println("propertyName:"+propertyName);
                        System.out.println("propertyValue:"+propertyValue);




                        Class<?> clazz = Class.forName(className);


                        //优先级id>name
                        String beanName = StrUtil.isNotEmpty(id) ? id : name;
                        if(StrUtil.isEmpty(beanName)){
                            beanName =StrUtil.lowerFirst(clazz.getSimpleName());
                        }

                        System.out.println("xml文件里 id ="+id);
                        System.out.println("xml文件里 name ="+name);
                        System.out.println("xml文件里 className ="+className);
                        System.out.println("xml文件里 propertyName ="+propertyName);
                        System.out.println("xml文件里 propertyValue ="+propertyValue);
                    }

                }
            }
        }
    }
}

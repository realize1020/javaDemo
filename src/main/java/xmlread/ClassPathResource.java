package xmlread;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) throws Exception {
        this(path,(ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) throws Exception {
        if(null == path){
            throw new Exception("Path 不能为空！");
        }
        this.path = path;
        this.classLoader = classLoader !=null? classLoader : Thread.currentThread().getClass().getClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if(classLoader ==null){
            classLoader = ClassPathResource.class.getClassLoader();
        }
        InputStream is = classLoader.getResourceAsStream(path);
        if(is == null){
            throw new FileNotFoundException(this.path + "文件不存在，无法打开文件");
        }
        return is;
    }
}

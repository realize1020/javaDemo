package fileCopy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCopy{
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String saveDir="D:\\2\\[HBNY-GZ-2022-6207]光大环保能源（乐山）有限公司中控室大屏与室外大屏更换";
                String target = saveDir.concat(File.separator).concat("公司1").concat(File.separator).concat("文件1.pdf");
                String folderPath=saveDir+"\\"+"公司1";
                File folder=new File(folderPath);
                Path path = Paths.get(folderPath);
                try {
                    Path pathCreate = Files.createDirectories(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!folder.exists()){
                    folder.mkdirs();
                }
                String filePath="c:\\bidFile\\2e3d573159374a37b2030d06cfa8b08a\\bidFile\\119d64d5738b48b5aeb1362eb7119afd\\MulPrice_53a3908a3c4e4e6792fce701b29b7830.pdf";
                fileCopy(filePath,target);
            }

            private void fileCopy(String absolutePath, String target) {
                // TODO Auto-generated method stub

                FileInputStream is=null;
                FileOutputStream fps=null;
                try {
                    is=new FileInputStream(absolutePath);
                    fps=new FileOutputStream(target);
                    byte[] b=new byte[1024];
                    while((is.read(b))!=-1){
                        fps.write(b);
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch bloc
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                }finally{
                    if(is!=null){
                        try {
                            is.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();

                        }
                    }
                    if(fps!=null){
                        try {
                            is.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();

                        }
                    }
                }

            }


        }).start();




    }


}

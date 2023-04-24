package createtime;

import java.beans.Encoder;
import java.io.*;
import java.util.StringTokenizer;

public class FileReadCreateTime {
    public static void main(String[] args) throws IOException {
        String commond="cmd /c dir C:\\bidFile\\eae7f508784d4d4c8c8149dcf51a1014\\reportFile\\11-山能评标报告-单个项目.docx /tc";
        //String commond="winchat";
        Process proc=Runtime.getRuntime().exec(commond);

        BufferedReader br=new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));
        String data="";
        for(int i=0;i<6;i++){
            data=br.readLine();
        }
        /*BufferedInputStream input=new BufferedInputStream(proc.getInputStream());
        DataInputStream inputStream=new DataInputStream(input);

        while(input.read()!=-1){
            inputStream.
        }*/

        System.out.println("提取的值为："+data);

        StringTokenizer st=new StringTokenizer(data);
        String date=st.nextToken();
        String time=st.nextToken();

        System.out.println("Creation Date"+date);
        System.out.println("Creation Time"+time);
        st.nextToken();

    }
}

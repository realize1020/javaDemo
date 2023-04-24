package fileupload.com;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class MultipartUploadSender {

    public static final int BUF_SIZE = 1024 * 1204;
    public static final long MULTIPART_SIZE =100 * 1024 * 1024;

    public static void main(String[] args) {

        String sourceFile = "D:\\book.zip";
        String targetPath = "D:\\book\\1\\";
        String newFilePath = "D:\\book\\1\\book_copy.zip";


        // 分片开始-方式1
        /*long current = System.currentTimeMillis();
        List<String> fileNames = MultipartUploadSender.fileSpilt(sourceFile, targetPath, MULTIPART_SIZE);
        System.out.println("普通IO方式用时（毫秒）：" + (System.currentTimeMillis() - current));*/


        //开始合并
       /* System.out.println("开始合并。。。。。");
        File[] fileList = fileFilter(targetPath, fileNames);
        mergeFile(fileList, newFilePath);
        System.out.println("合并完成！");*/

        // 分片开始-方式2
        long current2 = System.currentTimeMillis();
        List<String> fileNames = MultipartUploadSender.fileSpiltWithNio(sourceFile, targetPath, MULTIPART_SIZE);
        System.out.println("NIO方式用时（毫秒）:" + (System.currentTimeMillis() - current2));





        // 开始合并
        System.out.println("开始合并。。。。。");
        File[] fileList = fileFilter(targetPath, fileNames);
        mergeFileWithNio(fileList, newFilePath);
        System.out.println("合并完成！");
    }

    private static void mergeFileWithNio(File[] files, String newFilePath) {

        FileChannel outChannel =null;
        FileChannel inChannel =null;

        try{

            outChannel=new FileOutputStream(newFilePath).getChannel();
            for(File file:files){
                inChannel=new FileInputStream(file).getChannel();
                ByteBuffer bb=ByteBuffer.allocate(BUF_SIZE);
                while(inChannel.read(bb)!=-1){
                    bb.flip();
                    outChannel.write(bb);
                    bb.clear();
                }
                inChannel.close();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (outChannel != null) {
                    outChannel.close();
                }
                if (inChannel != null) {
                    inChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 合成方法：RandomAccessFile方式
     *
     * @param files       要合并的文件
     * @param newFilePath 新文件路径
     */
    private static void mergeFile(File[] files, String newFilePath) {

        RandomAccessFile in =null;
        RandomAccessFile out=null;

        try{

            out=new RandomAccessFile(newFilePath,"rw");
            for(File file : files){
                in=new RandomAccessFile(file,"r");
                int len = 0;
                byte[] bt=new byte[BUF_SIZE];
                while(-1 != (len = in.read(bt))){
                    out.write(bt,0,len);
                }
                in.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(in!=null){
                    in.close();
                }
                if(out !=null){
                    out.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * 过滤要合并的文件
     *
     * @param path      文件所在目录
     * @param fileNames 要过滤的文件
     * @return 过滤后的文件
     */
    private static File[] fileFilter(String path, List<String> fileNames) {
        File dir=new File(path);
        /*File[] fileArr=dir.listFiles((name)->{
            return fileNames.contains(name);
        });*/
        File[] fileArr = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return fileNames.contains(name);
            }
        });
        return fileArr;
    }




    /**
     * 普通IO分片方式
     *
     * @param sourceFile    要分片的文件
     * @param targetPath    输出路径
     * @param multipartSize 文件被切割的大小，单位：byte
     *                      <p>
     *                      model各个参数详解
     *                      r 代表以只读方式打开指定文件
     *                      rw 以读写方式打开指定文件
     *                      rws 读写方式打开，并对内容或元数据都同步写入底层存储设备
     *                      rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备
     */
    private static List<String> fileSpilt(String sourceFile, String targetPath, long multipartSize) {
        InputStream inputStream=null;
        FileOutputStream outputStream=null;
        List<String> fileNames=new LinkedList<>();
        try{

            RandomAccessFile raf=new RandomAccessFile(sourceFile,"r");
            long fileLength = raf.length();

            //每个文件分的片数。
            double total=(double)fileLength/multipartSize;

            //如果小数点大于1，整数加一 例如4.1 =》5
            int num=(int)Math.ceil(total);
            long pointe =0;

            for(int i=1;i<=num;i++){
                // 获取RandomAccessFile对象文件指针的位置，初始位置是0
                // 移动文件指针位置，pointe是跳过的长度
                raf.seek(pointe);

                //每次读取200M的大小
                byte[] buff=new byte[(int)multipartSize];
                int byteCount=0;
                if((byteCount=raf.read(buff))>0){
                    inputStream=new ByteArrayInputStream(buff,0,byteCount);
                    //String fileName=UUID.randomUUID().toString().replaceAll("-","");
                    String fileName=String.valueOf(i);
                    outputStream= new FileOutputStream(targetPath+fileName);
                    fileNames.add(fileName);
                    int ch=0;
                    byte[] bytes=new byte[BUF_SIZE];
                    while((ch=inputStream.read(bytes))>0){
                        outputStream.write(bytes,0,ch);
                    }
                    outputStream.close();
                    inputStream.close();
                }
                pointe=pointe+multipartSize;
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(outputStream!=null){
                    outputStream.close();
                }
                if(inputStream !=null){
                    inputStream .close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return fileNames;
    }



    private static List<String> fileSpiltWithNio(String sourceFile, String targetPath, long multipartSize) {
        File file=new File(sourceFile);
        FileOutputStream outputStream=null;
        FileChannel outputChannel =null;
        FileInputStream inputStream=null;
        FileChannel inputChannel=null;
        List<String> fileNames=new LinkedList<>();
        try{
            long splitSize=multipartSize;
            long startPoint=0;
            long fileLength=file.length();
            //每个文件分的片数。
            double total=(double) fileLength / multipartSize;

            //如果小数点大于1，整数加一 例如4.1 =》5
            int num = (int) Math.ceil(total);

            inputStream = new FileInputStream(file);
            inputChannel = inputStream.getChannel();
            for(int i=1;i<=num;i++){
                //String fileName=UUID.randomUUID().toString().replaceAll("-","");
                String fileName=String.valueOf(i);
                outputStream =new FileOutputStream(targetPath+fileName);
                fileNames.add(fileName);
                outputChannel=outputStream.getChannel();
                inputChannel.transferTo(startPoint,splitSize,outputChannel);
                startPoint += splitSize;
                outputChannel.close();
                outputStream.close();
            }

            inputChannel.close();
            inputStream.close();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputChannel != null) {
                    outputChannel.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputChannel != null) {
                    inputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return fileNames;
    }

}

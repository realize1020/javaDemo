package thread8;

public class test {

    public static void main(String[] args) {
        boolean downloadSignFileResult = true;
        boolean downloadFlag = DownloadFileUtill.startDownloadFileSync("", "", "", "正在下载报表文件");
        if(!downloadFlag) {
            System.out.println("下载失败");
            downloadSignFileResult = false;
        }else {
            System.out.println("下载成功");
            downloadSignFileResult = true;
        }
    }
}

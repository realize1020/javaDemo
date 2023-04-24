package thread3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        MMSCInfo msInfo1 =new MMSCInfo("1","1001.com",100);
        MMSCInfo msInfo2 =new MMSCInfo("2","1002.com",200);
        MMSCInfo msInfo3 =new MMSCInfo("3","1003.com",300);
        MMSCInfo msInfo4 =new MMSCInfo("4","1004.com",400);
        MMSCInfo msInfo5 =new MMSCInfo("5","1005.com",500);


        List<MMSCInfo> mmscInfoList =new ArrayList<>();

        mmscInfoList.add(msInfo1);
        mmscInfoList.add(msInfo2);
        mmscInfoList.add(msInfo3);
        mmscInfoList.add(msInfo1);
        mmscInfoList.add(msInfo4);
        mmscInfoList.add(msInfo5);


        MMSCRouter mmscRouter = new MMSCRouter();

    }
}

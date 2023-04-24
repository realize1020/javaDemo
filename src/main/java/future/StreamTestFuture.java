package future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class StreamTestFuture {

    public static String rpcCall(String ip, String param) {
        System.out.println(ip + " rpcCall: " + param);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }


    private static void NoCompletableFuture() {
        //1、生成ip列表
        List<String> ipList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ipList.add("192.169.0." + i);
        }


        //2、发起广播
        long start = System.currentTimeMillis();

        List<String> result=new ArrayList<>();
        for(String ip :ipList){
            result.add(rpcCall(ip,ip));

        }

        //3、輸出
        result.stream().forEach(r-> System.out.println(r));
        System.out.println("花费时间: "+(System.currentTimeMillis()-start)+"ms");
    }


    private static void UseCompletableFuture() {
        //1、生成ip列表
        List<String> ipList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ipList.add("192.169.0." + i);
        }

        //2、并发调用，发起广播
        long start = System.currentTimeMillis();

        List<CompletableFuture<String>> futureList = ipList.stream().map(ip -> CompletableFuture.supplyAsync(() -> rpcCall(ip, ip)))//同步转化为异步
                .collect(Collectors.toList());//收集结果

        //3、等待所有异步任务执行完毕
        List<String> result = futureList.stream()
                .map(future -> future.join())//同步等待结果
                .collect(Collectors.toList());

        //4、輸出
        result.stream().forEach(r-> System.out.println(r));
        System.out.println("花费时间: "+(System.currentTimeMillis()-start)+"ms");
    }





    public static void main(String[] args) {
        //没有使用多线程异步
        //NoCompletableFuture();


        //使用多线程异步
        UseCompletableFuture();



    }




}

package interfaceAndabstract;

public abstract class AbstrackVehicle implements Vehicle{
//    /**
//     * 交通工具启动
//     */
//    abstract void run();
//
//
//    /**
//     * 交通工具停止
//     */
//    abstract void stop();

    protected void fixed(){
        System.out.println("修理交通工具");
    }

    @Override
    public  void run() {
        System.out.println("run................");
    }

    @Override
    public void stop() {
        System.out.println("stop................");
    }
}

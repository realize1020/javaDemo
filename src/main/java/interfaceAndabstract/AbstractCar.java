package interfaceAndabstract;

public abstract class AbstractCar extends AbstrackVehicle{

    /**
     * 选择颜色
     */
    abstract void chooseColor();

    /**
     * 超级加速
     */
    protected void accelerate(){
        System.out.println("轿车开启超级加速功能！");
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void stop() {
        super.stop();
    }
}

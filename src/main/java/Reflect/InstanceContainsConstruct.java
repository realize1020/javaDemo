package Reflect;

public class InstanceContainsConstruct {

    public static void main(String[] args) {
        Class userClass = User.class;//如果User包含带参数的构造方法的话，通过Class对象实例化的过程就不成功。
        try {
            User user = (User)userClass.newInstance();
            System.out.println(user);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



    }


}

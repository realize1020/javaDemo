package java8;

import entity.User;

import java.util.Optional;

public class test {
    public static void main(String[] args) {

        User user =new User();
        //user.setName("zhangsan");
        User user1 = getUser(user);
        User user3 = getUser3(user);
        System.out.println(user1);
        System.out.println(user3);

    }


    public static User getUser(User user) {
        if(user!=null){
            String name = user.getName();
            if("zhangsan".equals(name)){
                return user;
            }
        }else{
            user = new User();
            user.setName("zhangsan");
        }
        return user;
    }


    public static User getUser2(User user) {
        if(user!=null&&"zhangsan".equals(user.getName())){
            return user;
        }else{
            user = new User();
            user.setName("zhangsan");
        }
        return user;
    }


    public static User getUser3(User user) {
        return Optional.ofNullable(user)
                .filter(u ->u.equals("zhangsan"))
                .orElseGet(()->{
                    User user1 = new User();
                    user1.setName("zhangsan");
                    return user1;
                });
    }




}

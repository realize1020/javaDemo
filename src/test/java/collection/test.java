package collection;

import entity.User;
import one.util.streamex.EntryStream;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class test {

    /**
     * 数据处理转换
     *
     * 将 List<Map<String,String>> list  转换成  Map<String,List<String>> res
     */
    @Test
    public void test1(){
        List<Map<String,String>> userList = new ArrayList<Map<String,String>>();
//        User user1 = new User("zhangsan","20");
//        User user2 = new User("李四","22");
//        userList.add(user1);
//        userList.add(user2);
        HashMap<String,String> map1 =new HashMap<String, String>();
        HashMap<String,String> map2 =new HashMap<String, String>();
        HashMap<String,String> map3 =new HashMap<String, String>();
        HashMap<String,String> map4 =new HashMap<String, String>();
        map1.put("key","name");
        map1.put("value","zhangsan");
        map2.put("key","name");
        map2.put("value","lisi");
        map3.put("key","age");
        map3.put("value","20");
        map4.put("key","age");
        map4.put("value","20");
        userList.add(map1);
        userList.add(map2);
        userList.add(map3);
        userList.add(map4);
        System.out.println(userList);

        //转换
        Map<String,List<String>> res =userList.stream().collect(Collectors.groupingBy(maps -> maps.get("key"),Collectors.collectingAndThen(Collectors.toList(),m -> {
            ArrayList<String> value = new ArrayList<String>();
            for(Map<String,String> map:m){
                value.add(map.get("value"));
            }
            return value;
        })));
        System.out.println(res);
    }


    @Test
    public void test2(){
        List<User> userList = new ArrayList<User>();
        User user1 = new User("zhangsan","20");
        User user2 = new User("zhangsan","22");
        User user3 = new User("wangwu","34");
        User user4 = new User("zhaoliu","31");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        //下面这两个统计分组后的区别,第一个是统计，第二个只返回根据姓名分组后map里的个数（有去重的效果）
        Map<String, Long> collect = userList.stream().collect(Collectors.groupingBy(User::getName, Collectors.counting()));
        Integer size = userList.stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(User::getName), map -> map.size()));
        System.out.println(collect);
        System.out.println(size);
    }


    /**
     * List转map
     */
    @Test
    public void test3(){
        List<User> userList = new ArrayList<User>();
        User user1 = new User("zhangsan","20");
        User user2 = new User("zhangsan","22");
        User user3 = new User("wangwu","34");
        User user4 = new User("zhaoliu","31");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        //Map<String, User> maps = userList.stream().collect(Collectors.toMap(User::getName, map -> map));
        //如果有重复的key，上面代码会报错，所以要下面这样处理(相当于list去重后，转化为map)
        Map<String, User> maps2 = userList.stream().collect(Collectors.toMap(User::getName, map -> map,(k1,k2)->k1));
        System.out.println(maps2);
    }

    @Test
    public void test4(){
        List<User> userList = new ArrayList<User>();
        User user1 = new User("zhangsan","20");
        User user2 = new User("zhangsan","22");
        User user3 = new User("wangwu","34");
        User user4 = new User("zhaoliu","31");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        List<User> distinctList = userList

                .stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(comparing(o-> o.getName()))), ArrayList::new))
                .stream().sorted(comparing(User::getName)).collect(Collectors.toList());


        Map<String, User> collect = userList

                .stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(comparing(o -> o.getName()))), ArrayList::new))
                .stream().sorted(comparing(User::getName).reversed()).collect(Collectors.toMap(User::getName, map -> map));

        System.out.println(distinctList);

        System.out.println(collect);

    }



    @Test
    public void test5(){
        List<User> userList = new ArrayList<User>();
        User user1 = new User("zhangsan","20");
        User user2 = new User("zhangsan2","22");
        User user3 = new User("wangwu","34");
        User user4 = new User("zhaoliu","31");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);


        List<String> collect = userList.stream().sorted(comparing(User::getAge,Comparator.reverseOrder())).map(User::getName).collect(Collectors.toList());

        System.out.println(collect);
    }


    /**
     * 合并两个map，如果有相同的key，按照第三个参数表达式进行构造
     */
    @Test
    public void test6() {
        User user1 = new User("zhangsan","20");
        User user2 = new User("lisi","22");
        User user3 = new User("wangwu","18");
        User user4 = new User("zhangsan","27");
        HashMap<String, User> map1 = new HashMap<String, User>();
        map1.put(user1.getName(),user1);
        map1.put(user2.getName(),user2);

        HashMap<String, User> map2 = new HashMap<String, User>();
        map2.put(user3.getName(),user3);
        map2.put(user4.getName(),user4);


        HashMap<String,User> map3 = new HashMap<>(map1);
        map2.forEach((key,value) -> {
            map3.merge(key,value,(v1,v2)->new User(v1.getName(),v2.getAge()));
        });

        System.out.println(map3);

    }


    /**
     * 合并两个map,Stream.concat
     */
    @Test
    public void test7() {
        User user1 = new User("zhangsan","20");
        User user2 = new User("lisi","22");
        User user3 = new User("wangwu","18");
        User user4 = new User("zhangsan","27");
        HashMap<String, User> map1 = new HashMap<String, User>();
        map1.put(user1.getName(),user1);
        map1.put(user2.getName(),user2);

        HashMap<String, User> map2 = new HashMap<String, User>();
        map2.put(user3.getName(),user3);
        map2.put(user4.getName(),user4);


        Map<String, User> collect = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> new User(value1.getName(), value2.getAge())));


        System.out.println(collect);
        collect.forEach((key,value) -> System.out.println(value.getAge()));
    }

    /**
     * 合并两个map,Stream.of
     */
    @Test
    public void test8() {
        User user1 = new User("zhangsan","20");
        User user2 = new User("lisi","22");
        User user3 = new User("wangwu","18");
        User user4 = new User("zhangsan","27");
        HashMap<String, User> map1 = new HashMap<String, User>();
        map1.put(user1.getName(),user1);
        map1.put(user2.getName(),user2);

        HashMap<String, User> map2 = new HashMap<String, User>();
        map2.put(user3.getName(),user3);
        map2.put(user4.getName(),user4);


        Map<String, User> collect = Stream.of(map1, map2)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (m1, m2) -> new User(m1.getName(), m2.getAge())));


        System.out.println(collect);
        collect.forEach((key,value) -> System.out.println(value.getAge()));
    }


    /**
     * 合并两个map,StreamEx
     */
    @Test
    public void test9() {
        User user1 = new User("zhangsan","20");
        User user2 = new User("lisi","22");
        User user3 = new User("wangwu","18");
        User user4 = new User("zhangsan","27");
        HashMap<String, User> map1 = new HashMap<String, User>();
        map1.put(user1.getName(),user1);
        map1.put(user2.getName(),user2);

        HashMap<String, User> map2 = new HashMap<String, User>();
        map2.put(user3.getName(),user3);
        map2.put(user4.getName(),user4);


        Map<String, User> map3 = EntryStream.of(map1)

                .append(EntryStream.of(map2))

                .toMap((e1, e2) -> e1);


        System.out.println(map3);
        map3.forEach((key,value) -> System.out.println(value.getAge()));
    }


    /**
     * 查找年龄最高的员工的姓名
     */
    @Test
    public void test10(){
        List<User> userList = new ArrayList<User>();
        User user1 = new User("zhangsan","21");
        User user2 = new User("zhangsan2","30");
        User user3 = new User("wangwu","34");
        User user4 = new User("zhaoliu","31");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);


        String userName = userList.stream()
                .collect(Collectors
                        .collectingAndThen(Collectors.maxBy(comparing(User::getAge)),
                                userOptional -> userOptional.get().getName()));

        System.out.println(userName);


        //下面第二种更加优雅一点
        String userName2 = userList.stream()
                .collect(Collectors
                        .collectingAndThen(Collectors.maxBy(comparing(User::getAge)),
                                (Optional<User> userOptional) -> userOptional.map(User::getName).orElse(null)));


        System.out.println(userName2);
    }


    /**
     * 需要根据两个集合中id值相同，就把第二个集合中的grade值赋给第一个集合，如果不匹配，默认grade值为0
     * 结果如下：
     * {grade=61, name=张三丰1, id=1}
     * {grade=62, name=张三丰2, id=2}
     * {grade=63, name=张三丰3, id=3}
     * {grade=64, name=张三丰4, id=4}
     * {grade=0, name=张三丰5, id=5}
     * {grade=0, name=张三丰6, id=6}
     * {grade=0, name=张三丰7, id=7}
     * {grade=0, name=张三丰8, id=8}
     */
    @Test
    public void test11(){
        List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
        for (int i=1;i<9;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("id",i);
            map.put("name","张三丰"+i);
            list1.add(map);
        }
        Stream<Map<String, Object>> s1 = list1.stream();
        list1.stream().forEach(map-> System.out.println(map));

        List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
        for (int i=1;i<5;i++){
            Map<String,Object> map2 = new HashMap<>();
            map2.put("id",i);
            map2.put("grade",i+60);
            list2.add(map2);
        }
        list2.stream().forEach(s-> System.out.println(s));


        /*List<Map<String, Object>> collect = list1.stream()
               .filter(m1 -> list2.stream().anyMatch(m2 -> {
                    if(m2.get("id").equals(m1.get("id"))){
                        m1.put("grade",m2.get("grade"));
                        return true;
                    }
                    return false;

                })).collect(Collectors.collectingAndThen(Collectors.toList(), list->list1.stream().filter(m1->list2.stream().noneMatch(m2->m1.get("id").equals(m2.get("id")))).map(m->{
                    m.put("grade",0);
                    list.add(m);
                    return list;
                })),ArrayList::new))
        collect.stream().forEach(map-> System.out.println(map));*/


        List<Map<String, Object>> collect = list1.stream().map(m1 -> {
            m1.put("grade", 0);
            list2.stream().filter(m2 -> m1.get("id").equals(m2.get("id"))).forEach(m -> m1.put("grade", m.get("id")));
            return m1;

        }).collect(Collectors.toList());

        collect.stream().forEach(map-> System.out.println(map));


    }


    /**
     * 两个集合求并集
     */
    @Test
    public void test12(){
        List<String> A = new ArrayList<>();
        A.add("1");
        A.add("2");
        A.add("3");
        A.add("4");
        List<String> B = new ArrayList<>();
        B.add("3");
        B.add("4");
        B.add("5");
        B.add("6");
        B.add("7");


        A.addAll(B);
        List<String> collect = A.stream().distinct().collect(Collectors.toList());

        System.out.println(collect);


    }



    /**
     * 两个集合求交集
     */
    @Test
    public void test13(){
        List<String> A = new ArrayList<>();
        A.add("1");
        A.add("2");
        A.add("3");
        A.add("4");
        List<String> B = new ArrayList<>();
        B.add("3");
        B.add("4");
        B.add("5");
        B.add("6");
        B.add("7");


        List<String> collect = A.stream().filter(B::contains).collect(Collectors.toList());

        System.out.println(collect);


    }



    /**
     * 两个集合求差集
     */
    @Test
    public void test14(){
        List<String> A = new ArrayList<>();
        A.add("1");
        A.add("2");
        A.add("3");
        A.add("4");
        List<String> B = new ArrayList<>();
        B.add("3");
        B.add("4");
        B.add("5");
        B.add("6");
        B.add("7");


        List<String> collect = A.stream().filter(a->!B.contains(a)).collect(Collectors.toList());

        System.out.println(collect);


    }



    /**
     * 对象集合求并集并按字段排序
     */
    @Test
    public void test15(){
        List<User> A = new ArrayList<>();
        A.add(new User("赵", "1"));
        A.add(new User("杜", "2"));

        List<User> B = new ArrayList<>();
        B.add(new User("杜", "2"));
        B.add(new User("周", "3"));

        A.addAll(B);

        List<User> collect = A.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<User>(comparing(User::getName))), ArrayList::new))
                .stream()
                .sorted(comparing(User::getAge))
                .collect(Collectors.toList());

        System.out.println(collect);


    }

    /**
     * 对象集合求交集
     */
    @Test
    public void test16(){
        List<User> A = new ArrayList<>();
        A.add(new User("赵", "1"));
        A.add(new User("杜", "2"));
        A.add(new User("李", "3"));


        List<User> B = new ArrayList<>();
        B.add(new User("杜", "2"));
        B.add(new User("周", "3"));
        B.add(new User("李", "3"));

        //下面这种以对象为基本单位，集合是否包含对象来判断是不行的。
        List<User> collect = A.stream().filter(a -> B.stream().map(b -> {
            return A.contains(b);
        }).findAny().get()).collect(Collectors.toList());

        //得以对象属性为基本单位，判断是否相等。但是findAny()只能拿出一个
        List<User> collect2 = A.stream().filter(a -> B.stream().map(b -> {
            return a.getName().equals(b.getName());
        }).findAny().get()).collect(Collectors.toList());

        //方法1
        List<User> collect3 = A.stream().filter(a -> B.stream().map(b->b.getName()).collect(Collectors.toList()).contains(a.getName())).collect(Collectors.toList());

        //方法2
        List<User> collect4 = A.stream().filter(a -> B.stream().map(User::getName).anyMatch(b -> b.equals(a.getName()))).collect(Collectors.toList());

        System.out.println(collect4);


    }


    @Test
    public void test17() {

        //list1是sap的数据
        ArrayList<SapZoutbudget> list1 = new ArrayList();
        SapZoutbudget sp1 = new SapZoutbudget(1, "One", BigDecimal.ONE);
        SapZoutbudget sp2 = new SapZoutbudget(2, "Two", BigDecimal.TEN);
        SapZoutbudget sp3 = new SapZoutbudget(3, "Three", BigDecimal.ONE);
        SapZoutbudget sp4 = new SapZoutbudget(4, "Four", BigDecimal.ZERO);
        SapZoutbudget sp5 = new SapZoutbudget(5, "Five", BigDecimal.ZERO);
        SapZoutbudget sp6 = new SapZoutbudget(6, "Six", BigDecimal.ZERO);
        SapZoutbudget sp7 = new SapZoutbudget(6, "Six", BigDecimal.ZERO);
        SapZoutbudget sp11 = new SapZoutbudget(6, "eleven", BigDecimal.ZERO);

        list1.add(sp1);
        list1.add(sp2);
        list1.add(sp3);
        list1.add(sp4);
        list1.add(sp5);
        list1.add(sp6);
        list1.add(sp7);
        list1.add(sp11);


        //list2是年度计划
        ArrayList<SapZoutbudget> list2 = new ArrayList();
//        SapZoutbudget sp8 = new SapZoutbudget(8, "eight", BigDecimal.ZERO);
//        SapZoutbudget sp9 = new SapZoutbudget(9, "nine", BigDecimal.ZERO);
//        SapZoutbudget sp10 = new SapZoutbudget(10, "ten", BigDecimal.ZERO);
        SapZoutbudget s1 = new SapZoutbudget(1, "One", BigDecimal.ONE);
        SapZoutbudget s2 = new SapZoutbudget(2, "Two", BigDecimal.TEN);
        SapZoutbudget s3 = new SapZoutbudget(3, "Three", BigDecimal.ONE);


        /*list2.add(sp1);
        list2.add(sp2);
        list2.add(sp3);
        list2.add(sp4);
        list2.add(sp5);
        list2.add(sp6);*/
        list2.add(s1);
        list2.add(s2);
        list2.add(s3);

        List<String> costChecks = new ArrayList<>();


        TreeSet<SapZoutbudget> collect1 = list1.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        //根据成本中心编码和承诺项编码构造TreeSet
                        Comparator.comparing(sapZoutbudget
                                -> getCostAllocation(sapZoutbudget.getI(), sapZoutbudget.getS()
                        )))), TreeSet::new));


        collect1.stream().forEach(System.out::println);

        System.out.println("---------------------------------------");

        List<SapZoutbudget> collect = collect1.stream()
                //过滤出上一步结果集中与年度计划数据中的交集
                .filter(key -> !list2.stream()
                        .map(budgetAnnualPlan -> getCostAllocation(budgetAnnualPlan.getI(), budgetAnnualPlan.getS()))
                        .collect(Collectors.toList()).contains(key))
                .collect(Collectors.toList());

        collect.stream().forEach(System.out::println);

    }

    @Test
    public void test18(){

        //list1是sap的数据
        ArrayList<SapZoutbudget> list1=new ArrayList();
        SapZoutbudget sp1=new SapZoutbudget(1,"One",BigDecimal.ONE);
        SapZoutbudget sp2=new SapZoutbudget(2,"Two",BigDecimal.TEN);
        SapZoutbudget sp3=new SapZoutbudget(3,"Three",BigDecimal.ONE);
        SapZoutbudget sp4=new SapZoutbudget(4,"Four",BigDecimal.ZERO);
        SapZoutbudget sp5=new SapZoutbudget(5,"Five",BigDecimal.ZERO);
        SapZoutbudget sp6=new SapZoutbudget(6,"Six",BigDecimal.ZERO);
        SapZoutbudget sp7=new SapZoutbudget(6,"Six",BigDecimal.ZERO);
        SapZoutbudget sp11=new SapZoutbudget(6,"eleven",BigDecimal.ZERO);

        list1.add(sp1);
        list1.add(sp2);
        list1.add(sp3);
        list1.add(sp4);
        list1.add(sp5);
        list1.add(sp6);
        list1.add(sp7);
        list1.add(sp11);


        //list2是年度计划
        ArrayList<SapZoutbudget> list2=new ArrayList();
        SapZoutbudget sp8=new SapZoutbudget(8,"eight",BigDecimal.ZERO);
        SapZoutbudget sp9=new SapZoutbudget(9,"nine",BigDecimal.ZERO);
        SapZoutbudget sp10=new SapZoutbudget(10,"ten",BigDecimal.ZERO);


        /*list2.add(sp1);
        list2.add(sp2);
        list2.add(sp3);
        list2.add(sp4);
        list2.add(sp5);
        list2.add(sp6);*/
        list2.add(sp8);
        list2.add(sp9);
        list2.add(sp10);

        List<String> costChecks=new ArrayList<>();

        Optional.ofNullable(list1.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        //根据成本中心编码和承诺项编码构造TreeSet
                        Comparator.comparing(sapZoutbudget
                                -> getCostAllocation(sapZoutbudget.getI(), sapZoutbudget.getS()
                        )))), sapZoutbudgetTreeSet -> sapZoutbudgetTreeSet.stream()
                        .map(sapZoutbudget -> getCostAllocation(sapZoutbudget.getI(), sapZoutbudget.getS()))
                        ////根据TreeSet构造List<String>的结果集，储存成本中心编码-承诺项编码
                        .collect(Collectors.collectingAndThen(Collectors.toList(),list->list.stream()
                                //过滤出上一步结果集中与年度计划数据中的交集
                                .filter(key->!list2.stream()
                                        .map(budgetAnnualPlan->getCostAllocation(budgetAnnualPlan.getI(),budgetAnnualPlan.getS()))
                                        .collect(Collectors.toList()).contains(key))
                                .collect(Collectors.collectingAndThen(Collectors.toList(),
                                        //对交集数据进行两次操作
                                        //年度计划中不存在相同成本中心编码-承诺向的数据和sap中不存在成本中心编码-承诺项编码的数据进行两次操作
                                        intersect -> list2.stream()
                                                .map(budgetAnnualPlan->getCostAllocation(budgetAnnualPlan.getI(),budgetAnnualPlan.getS()))
                                                .filter(x->!intersect.contains(x))
                                                .collect(Collectors.collectingAndThen(Collectors.toList(),
                                                        //对sap中不存在的成本中心编码-承诺向编码数据
                                                        //这个annualDiff是年度计划表的差集,继续获得sapZoutbudgetTreeSet的差集
                                                        annualDiff -> Stream.of(sapZoutbudgetTreeSet.stream()
                                                                .map(sapZoutbudget -> getCostAllocation(sapZoutbudget.getI(),sapZoutbudget.getS()))
                                                                .filter(x->!intersect.contains(x))
                                                                //对于sap中存在，年度计划中不存在的数据（sap的差集）加入sap1-12期的金额全部为0或null视为无异常
                                                                .filter(x->{
                                                                    //缺少Hutool工具
                                                                    //String cost=CollUtil.get(StrUtil.split(x,StrPool.DASHED),0);
                                                                    //String allocation=CollUtil.get(StrUtil.split(x,StrPool.DASHED),1);
                                                                    String cost=x.substring(0,x.indexOf("-"));
                                                                    String allocation=x.substring(x.indexOf("-")+1,x.length());
                                                                    BigDecimal bigDecimal = list1.stream()
                                                                            .filter(s -> s.getI().equals(cost) && s.getS().equals(allocation))
                                                                            .map(SapZoutbudget::getMoney)
                                                                            .filter(money -> money != null && money.compareTo(BigDecimal.ZERO) != 0)
                                                                            .findAny().orElse(null);
                                                                    return bigDecimal!=null;
                                                                }).collect(Collectors.toList()),annualDiff)
                                                                .flatMap(Collection::stream)
                                                                .collect(Collectors.toList())))))))))
                .stream().map(result->result.substring(0,result.indexOf("-")))
                .distinct()
                .collect(Collectors.toList()))
                .ifPresentOrElse(costChecks::addAll,()-> System.out.println(
                        "*********************onCheck end***********************年度计划-sap数据不存在差集数据"));

        System.out.println("结果："+costChecks);

    }



    @Test
    public void test19() {

    }


    public static String getCostAllocation(Integer i,String s){
        return i.toString()+"-"+s;
    }


    }




    class SapZoutbudget{
        private Integer i;
        private String s;

        private BigDecimal money;

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public Integer getI() {
            return i;
        }

        public void setI(Integer i) {
            this.i = i;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public SapZoutbudget(Integer i, String s,BigDecimal money) {
            this.i = i;
            this.s = s;
            this.money=money;
        }

        @Override
        public String toString() {
            return "SapZoutbudget{" +
                    "i=" + i +
                    ", s='" + s + '\'' +
                    ", money=" + money +
                    '}';
        }
    }

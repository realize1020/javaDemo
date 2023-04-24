/*
授权声明：
本源码系《Java多线程编程实战指南（设计模式篇）第2版》一书（ISBN：978-7-121-38245-1，以下称之为“原书”）的配套源码，
欲了解本代码的更多细节，请参考原书。
本代码仅为原书的配套说明之用，并不附带任何承诺（如质量保证和收益）。
以任何形式将本代码之部分或者全部用于营利性用途需经版权人书面同意。
将本代码之部分或者全部用于非营利性用途需要在代码中保留本声明。
任何对本代码的修改需在代码中以注释的形式注明修改人、修改时间以及修改内容。
本代码可以从以下网址下载：
https://github.com/Viscent/javamtp
http://www.broadview.com.cn/38245
*/

package thread3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 彩信中心路由规则管理器 模式角色：ImmutableObject.ImmutableObject
 */
public final class MMSCRouter {
    // 用volatile修饰，保证多线程环境下该变量的可见性
    private static volatile MMSCRouter instance = new MMSCRouter();
    // 维护手机号码前缀到彩信中心之间的映射关系
    private final Map<String, MMSCInfo> routeMap;

    public MMSCRouter() {
        // 将数据库表中的数据加载到内存，存为Map
        this.routeMap = MMSCRouter.retrieveRouteMapFromDB();
    }

    private static Map<String, MMSCInfo> retrieveRouteMapFromDB() {
        Map<String, MMSCInfo> map = new HashMap<String, MMSCInfo>();
        // 省略其他代码
        //模拟从数据库里查出
        MMSCInfo msInfo1 =new MMSCInfo("1001","1001.com",100);
        MMSCInfo msInfo2 =new MMSCInfo("1002","1002.com",200);
        MMSCInfo msInfo3 =new MMSCInfo("1003","1003.com",300);
        MMSCInfo msInfo4 =new MMSCInfo("1004","1004.com",400);
        MMSCInfo msInfo5 =new MMSCInfo("1005","1005.com",500);

        map.put("1",msInfo1);
        map.put("2",msInfo2);
        map.put("3",msInfo3);
        map.put("4",msInfo4);
        map.put("5",msInfo5);

        return map;
    }

    public static MMSCRouter getInstance() {

        return instance;
    }

    /**
     * 根据手机号码前缀获取对应的彩信中心信息
     * 
     * @param msisdnPrefix
     *            手机号码前缀
     * @return 彩信中心信息
     */
    public MMSCInfo getMMSC(String msisdnPrefix) {
        return routeMap.get(msisdnPrefix);

    }

    /**
     * 将当前MMSCRouter的实例更新为指定的新实例
     * 
     * @param newInstance
     *            新的MMSCRouter实例
     */
    public static void setInstance(MMSCRouter newInstance) {
        instance = newInstance;
    }

    private static Map<String, MMSCInfo> deepCopy(Map<String, MMSCInfo> m) {
        Map<String, MMSCInfo> result = new HashMap<String, MMSCInfo>();
        for (String key : m.keySet()) {
            result.put(key, new MMSCInfo(m.get(key)));
        }
        return result;
    }

    public Map<String, MMSCInfo> getRouteMap() {
        // 做防御性拷贝
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }

}

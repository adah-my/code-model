package xiaoce.middle.basal.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装ThreadLocal
 * 为什么要封装ThreadLocal？
 * 1. 对于Thread，如果希望再Intercepter中存入User Info并在Service层通过ThreadLocal把UserInfo勾出来，必须保证Intercepter和Service此时用的是同一个ThreadLocal
 * 2.原生的ThreadLocal无法满足复杂的业务操作
 */
public class ThreadLocalUtil
{
    private ThreadLocalUtil(){

    }

    /**
     * 将ThreadLocal泛型指定为String，那么造了一个ThreadLocalMap后，这个map只能存 threadLocal："这是字符串" 这样的键值对
     * 将ThreadLocal泛型指定为Integer，那么造了一个ThreadLocalMap后，这个map只能存 threadLocal：111111 这样的键值对
     *
     * 单纯的value会发送值覆盖，所以我们使用 Map<String, Object> 作为value
     */
    private static final ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new ThreadLocal<>();

    public static void put(String key, Object object){
        /**
         * 1.从Thread中取出ThreadLocalMap
         * 2.ThreadLocalMap.Entry e = map.getEntry(this); 把自己（THREAD_CONTEXT）作为key，取出自己的value， 此时是一个Map<String, Object>
         * 3.所以最终THREAD_CONTEXT.get()返回Map<String, Object> map
         */
        Map<String, Object> map = THREAD_CONTEXT.get();
        // 第一次从ThreadLocalMap中根据threadLocal去除的value可能是null
        if (map == null){
            map = new HashMap<>();
            // 把map作为value放进去
            THREAD_CONTEXT.set(map);
        }

        map.put(key, object);
    }

    /**
     * 取出线程变量
     * @param key
     * @return
     */
    public static Object get(String key){
        // 先得到Map
        Map<String, Object> map = THREAD_CONTEXT.get();
        // 从Map中得到value
        return map != null ? map.get(key) : null;
    }

    /**
     * 移除当前线程的指定变量
     * @param key
     */
    public static void remove(String key){
        Map<String, Object> map = THREAD_CONTEXT.get();
        map.remove(key);
    }

    /**
     * 移除当前线程的所有变量
     *
     */
    public static void clear(){
        THREAD_CONTEXT.remove();
    }
}

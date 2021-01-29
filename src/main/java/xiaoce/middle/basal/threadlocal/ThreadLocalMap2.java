package xiaoce.middle.basal.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalMap2
{

    private ThreadLocalMap2()
    {
    }

    /**
     * ThreadLocal的静态方法withinittial()会返回一个SuppliedThreadLocal对象
     * 而SuppliedThreadLocal<T> extends ThreadLocal<T>
     * 我们存进去的Map会作为的返回值
     * protect T initialValue(){
     *     return supplier.get();
     * }
     * 所以也相当于重写了initialValue()
     */
    public final static ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal.withInitial(() -> new HashMap<>(8));

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static Object get(String key){
        return THREAD_LOCAL.get().get(key);
    }

    /**
     * put操作
     * @param key
     * @param value
     */
    public static void put(String key, Object value){
        THREAD_LOCAL.get().put(key, value);
    }

    /**
     * 清除map里的某个值
     * @param key
     * @return
     */
    public static Object remove(String key){
        return THREAD_LOCAL.get().remove(key);
    }

    /**
     * 清除map中所有的值
     */
    public static void clear(){
        THREAD_LOCAL.get().clear();
    }

    /**
     * 移除整个map
     */
    public static void clearAll(){
        THREAD_LOCAL.remove();
    }
}

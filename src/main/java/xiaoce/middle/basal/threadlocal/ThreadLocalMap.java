package xiaoce.middle.basal.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 之前封装ThreadLocal时一直在解决两个问题：
 * 1.原生的ThreadLocal对于每个Thread的操作是基于单值的key-value，而我们期望基于key-MapValue的操作
 * 2.如果不重写initValue()，需要在外部处理Map的初始化问题
 *
 * 对于initValue()的重写，其实不要专门写一个内部类(很多人不习惯内部类)，有两种替代方式：
 * 1.给THREAD_CONTEXT赋值时，直接new ThreadLocal()并用匿名类方式重写initValue
 * 2.让ThreadLocalUtil继承ThreadLocal，然后重写initValue()
 * 第一种最简单，这里演示第二种
 */
public class ThreadLocalMap extends ThreadLocal<Map<String, Object>>
{
    private ThreadLocalMap(){

    }

    private final static ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new ThreadLocalMap();

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static Object get(String key){
        return THREAD_CONTEXT.get().get(key);
    }

    /**
     * put操作
     * @param key
     * @param value
     */
    public static void put(String key, Object value){
        THREAD_CONTEXT.get().put(key, value);
    }

    /**
     * 清除map中的某个值
     * @param key
     * @return
     */
    public static Object remove(String key){
        return THREAD_CONTEXT.get().remove(key);
    }

    /**
     * 清除整个Map<String, Object>
     */
    public static void clear(){
        THREAD_CONTEXT.get().clear();
    }

    /**
     * 清除整个ThreadLocalMap的内容
     */
    public static void clearAll(){
        THREAD_CONTEXT.remove();
    }

    @Override
    protected Map<String, Object> initialValue(){
        return new HashMap<String, Object>(8){
            private static final long serialVersionUID = 3637958959138295593L;

            @Override
            public Object put(String key, Object value) {
                return super.put(key, value);
            }

        };
    }

}

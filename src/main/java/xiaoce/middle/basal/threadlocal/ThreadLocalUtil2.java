package xiaoce.middle.basal.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 第二版
 */
public class ThreadLocalUtil2
{
    private ThreadLocalUtil2(){

    }

    /**
     * 注意右边new的不是原生的ThreadLocal，而是我自定义的MapThreadLocal，它继承自ThreadLocal
     */
    private static final ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new MapThreadLocal();

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static Object get(String key){
        // getContextMap()表示要先获取THREAD_CONTEXT的value，也就是Map<String, Object>。然后再从Map<String, Object>中根据key获取
        return getContextMap().get(key);
    }

    /**
     * 添加key value
     * @param key
     * @param value
     */
    public static void put(String key, Object value){
        getContextMap().put(key, value);
    }

    /**
     * 清除map中某个值
     * @param key
     * @return
     */
    public static Object remove(String key){
        return getContextMap().remove(key);
    }

    /**
     * 清除整个Map<String, Object>
     */
    public static void remove(){
        getContextMap().clear();
    }

    /**
     * 从ThreadLocalMap中获取到Map<String, Object>
     * @return
     */
    private static Map<String, Object> getContextMap(){
        return THREAD_CONTEXT.get();
    }

    /**
     * 内部类，继承自ThreadLocal，和第一版一样，仍旧指定value为Map<Stirng, Object>
     * 之所以要自定义MapThreadLocal，是为了重写原生的ThreadLocalinitialValue()
     * 把ThreadLocal第一版中判断null的操作隐藏掉，让代码优雅一些（但对于初学者来说，理解难度也提升了）
     */
    private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {

        @Override
        protected Map<String, Object> initialValue(){
            return new HashMap<String, Object>(8){
                private static final long serialVersionUID = 3637958959138295593L;

                @Override
                public Object put(String key, Object value){
                    return super.put(key, value);
                }
            };
        }
    }

}

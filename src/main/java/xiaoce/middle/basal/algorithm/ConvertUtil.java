package xiaoce.middle.basal.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConvertUtil
{
    private ConvertUtil(){

    }

    /**
     * 将List转为Map
     * @param list          原数据
     * @param keyExtractor  key的抽取规则
     * @param <K>           key
     * @param <V>           value
     * @return
     */
    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor){
        if (null == list || list.isEmpty()){
            return new HashMap<>();
        }
        HashMap<K, V> map = new HashMap<>();
        for (V element : list){
            K key = keyExtractor.apply(element);
            if (null == key){
                continue;
            }
            map.put(key, element);
        }
        return map;
    }

    /**
     * 将List转为Map，可以指定过滤规则
     * @param list          原数据
     * @param keyExtractor  key的抽取规则
     * @param predicate     过滤规则
     * @param <K>           key
     * @param <V>           value
     * @return
     */
    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor, Predicate<V> predicate){
        if (null == list || list.isEmpty()){
            return new HashMap<>();
        }
        HashMap<K, V> map = new HashMap<>();
        for (V element : list){
            K key = keyExtractor.apply(element);
            if (null == key || !predicate.test(element)){
                continue;
            }
            map.put(key, element);
        }
        return map;
    }

    /**
     * 将List映射为List， 比如List<Person> personList转为List<String> nameList
     * @param originList    原数据
     * @param mapper        映射数据
     * @param <T>           原数据的元素类型
     * @param <R>           新数据的元素类型
     * @return
     */
    public static <T, R> List<R> resultToList(List<T> originList, Function<T, R> mapper){
        if (null == originList || originList.isEmpty()){
            return new ArrayList<>();
        }
        ArrayList<R> newList = new ArrayList<>(originList.size());
        for (T originElement : originList){
            R newElement = mapper.apply(originElement);
            if (null == newElement){
                continue;
            }
            newList.add(newElement);
        }
        return newList;
    }

    /**
     * 将List映射为List， 比如List<Person> personList转为List<String> nameList
     * @param originList    原数据
     * @param mapper        映射数据
     * @param predicate     过滤规则
     * @param <T>           原数据的元素类型
     * @param <R>           新数据的元素类型
     * @return
     */
    public static <T, R> List<R> resultToList(List<T> originList, Function<T, R> mapper, Predicate<T> predicate){
        if (null == originList || originList.isEmpty()){
            return new ArrayList<>();
        }
        ArrayList<R> newList = new ArrayList<>();
        for (T originElement : originList){
            R newElement = mapper.apply(originElement);
            if (null == newElement || !predicate.test(originElement)){
                continue;
            }
            newList.add(newElement);
        }
        return newList;
    }


    private static List<Person> list;

    static{
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 20, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

    public static void main(String[] args)
    {
        Map<String, Person> nameToPersonMap = listToMap(list, Person::getName);
        System.out.println(nameToPersonMap);

        Map<String, Person> personGt18 = listToMap(list, Person::getName, person -> person.getAge() >= 18);
        System.out.println(personGt18);
    }

    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person{
        private String name;
        private Integer age;
        private String address;
        private Double salary;
    }
}

package xiaoce.middle.basal.streamapi.basic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamCollectTest
{

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 20, "温州", 888.8));
        list.add(new Person("iron", 25, "杭州", 777.7));
        list.add(new Person("iron", 42, "宁波", 888.8));
    }

    /**
     * collect最常用的四个方法：
     * Collectors.toList()、Collectors.toSet()、Collectors.toMap()、Collectors.joining()
     * @param args
     */
    public static void main(String[] args)
    {
        // 最常用的4个方法

        // 把结果收集为List
        List<String> toList = StreamCollectTest.list.stream().map(Person::getAddress).collect(Collectors.toList());
        System.out.println(toList);

        // 把结果收集为Set
        Set<String> toSet = list.stream().map(Person::getAddress).collect(Collectors.toSet());
        System.out.println(toSet);

        // 把结果收集为Map，前面是key，后面是value，如果你希望value是具体的某个字段，可以改为toMap(Person::getName, person->person.getAge())
//        Map<String, Integer> toMap = list.stream().collect(Collectors.toMap(Person::getName, Person::getAge));
//        System.out.println(toMap);

        // 把结果收集起来，并用指定分隔符拼接
        String result = list.stream().map(Person::getAddress).collect(Collectors.joining("#"));
        System.out.println(result);

        // 关于Collectors.toMap()的小坑
        // 预计key有可能冲突时，就要提前定好key的保留策略：可以覆盖还是key丢弃
//        Map<String, Person> toMap2 = list.stream().collect(Collectors.toMap(Person::getName, person -> person));
//        System.out.println(toMap2);

        // 保留前key
        Map<String, Person> toMap3 = list.stream().collect(Collectors.toMap(Person::getName, person -> person, (preKey, nextKey) -> preKey));
        System.out.println(toMap3);
        // 保留后key
        Map<String, Person> toMap4 = list.stream().collect(Collectors.toMap(Person::getName, person -> person, (preKey, nextKey) -> nextKey));
        System.out.println(toMap4);
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


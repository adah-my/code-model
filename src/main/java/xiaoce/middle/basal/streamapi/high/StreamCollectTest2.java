package xiaoce.middle.basal.streamapi.high;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

public class StreamCollectTest2
{

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 20, "温州", 888.8));
        list.add(new Person("iron", 25, "杭州", 777.7));
        list.add(new Person("man", 42, "宁波", 888.8));
    }

    /**
     * 按字段分组、
     * 按条件分组
     * @param args
     */
    public static void main(String[] args)
    {
        // GROUP BY adress
        Map<String, List<Person>> groupByAddr = list.stream().collect(Collectors.groupingBy(Person::getAddress));
        System.out.println(groupByAddr);

        // Group by address,age
        Map<String, Map<Integer, List<Person>>> doubleGroupBy = list.stream()
                .collect(Collectors.groupingBy(Person::getAddress, Collectors.groupingBy(Person::getAge)));
        System.out.println(doubleGroupBy);

        // 简单来说就是collect(groupingBy(xx)) => collect(groupingby(xx, groupingBy(yy))), 嵌套分组

        // 解决了按字段分组、按多个字段分组，我们再考虑一个问题：有时我们的分组不是某个字段，而是某个字段是否满足xx条件
        // 比如 年龄大于等于18的是成年人，小于18的是未成年人
        Map<Boolean, List<Person>> adultsAndTeenagers = list.stream().collect(Collectors.partitioningBy(person -> person.getAge() >= 20));
        System.out.println(adultsAndTeenagers);
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


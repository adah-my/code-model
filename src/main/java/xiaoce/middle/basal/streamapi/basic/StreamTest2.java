package xiaoce.middle.basal.streamapi.basic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest2
{

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("甲", 18, "杭州", 999.9));
        list.add(new Person("乙", 20, "温州", 888.8));
        list.add(new Person("丙", 25, "杭州", 777.7));
        list.add(new Person("丁", 42, "宁波", 888.8));
    }

    public static void main(String[] args)
    {
        // 先熟悉中间操作

        // 1.先获取流（不用管其他乱七八糟的创建方式，记住这一个就能应付95%的场景）
        Stream<Person> stream = list.stream();
        // 2.过滤年龄大于18岁的
        Stream<Person> filterByAgeStream = stream.filter(person -> person.getAge() > 18);
        // 3.只要名字不需要整个Person对象
        Stream<String> nameStream = filterByAgeStream.map(Person::getName);
        // 4.现在返回值是Stream<String>，没法直接使用，帮我收集成List<String>
        List<String> nameList = nameStream.collect(Collectors.toList());

        // 现在还对collect()为什么传递Collectors.toList()感到懵逼吗？

        // 链式操作
        List<String> chainList = list.stream().filter(person -> person.getAge() > 20).map(Person::getName).collect(Collectors.toList());

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


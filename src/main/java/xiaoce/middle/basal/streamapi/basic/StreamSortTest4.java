package xiaoce.middle.basal.streamapi.basic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSortTest4
{

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 20, "温州", 888.8));
        list.add(new Person("iron", 25, "杭州", 777.7));
        list.add(new Person("man", 42, "宁波", 888.8));
    }

    public static void main(String[] args)
    {
        // 直接链式操作
        List<String> nameList = list.stream().filter(person -> person.getAge() > 18).map(Person::getName).collect(Collectors.toList());
        System.out.println(nameList);

        // 按姓名排序
        List<String> collect = list.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(Collectors.toList());
        System.out.println(collect);

        // 优化一下：sorted有重载方法，是sorted(Comparator)
        // 上面Lambda其实就是调用了sorted(Comparator)，用Lambda给Comparator接口赋值
        // 但Comparator还提供了一些方法，能返回Comparator实例
        List<String> optimizedNameList = list.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(optimizedNameList);

        // 又是一样的套路，Comparator.reverseOrder()返回的其实是Comparator！！

        // 来个正常的，使用Comparator.comparing()
        List<String> result = list.stream()
                .filter(person -> person.getAge() > 19)
                .map(Person::getName)
                .sorted(Comparator.comparing(t -> t, (str1, str2) -> str1.length() - str2.length()))
                .collect(Collectors.toList());
        System.out.println(result);

        // 我去，更麻烦了！！
        // 不急，我们先了解上面案例中Comparator的两个参数
        // 第一个是Function映射，就是指定要排序的字段，由于经过上一步map操作，已经是name了，就不需要影射了，所以是 t -> t
        // 第二个是比较规则

        // 我们把map和sorted调换一下顺序，看起来就不那么别扭了
        List<String> result2 = list.stream()
                .filter(person -> person.getAge() > 18)
                .sorted(Comparator.comparing(Person::getName, String::compareTo).reversed())
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(result2);

        // 为什么Comparator.comparing().reversed()可以链式调用
        // 上面说了，因为Comparator.comparing()返回的还是Comparator对象
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


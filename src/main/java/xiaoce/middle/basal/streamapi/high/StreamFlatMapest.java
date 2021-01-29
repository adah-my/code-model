package xiaoce.middle.basal.streamapi.high;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

public class StreamFlatMapest
{

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9, new ArrayList<>(Arrays.asList("成年人", "学生", "男性"))));
        list.add(new Person("am", 20, "温州", 888.8, new ArrayList<>(Arrays.asList("成年人", "打工人", "踏入社会"))));
        list.add(new Person("iron", 17, "杭州", 777.7, new ArrayList<>(Arrays.asList("未成年人", "学生"))));
        list.add(new Person("man", 42, "宁波", 888.8, new ArrayList<>(Arrays.asList("成年人", "不惑", "男性"))));
    }

    /**
     * 简单地说，flatMap就是把多个流合并成一个流
     * @param args
     */
    public static void main(String[] args)
    {
        Set<String> allTags = list.stream().flatMap(person -> person.getTags().stream()).collect(Collectors.toSet());
        System.out.println(allTags);
        
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person{
        private String name;
        private Integer age;
        private String address;
        private Double salary;
        // 个人标签
        private List<String> tags;
    }
}


package xiaoce.middle.basal.streamapi.basic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xiaoce.primary.spring.bean.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest
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
        List<Person> result = list.stream().filter(person -> person.getAge() > 20).collect(Collectors.toList());
        System.out.println(result);

        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Integer integer = integers.stream().reduce((x, y) -> x + y).orElse(1);
        System.out.println(integer);

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


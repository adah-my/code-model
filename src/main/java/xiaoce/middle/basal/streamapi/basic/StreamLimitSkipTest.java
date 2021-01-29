package xiaoce.middle.basal.streamapi.basic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamLimitSkipTest
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
        List<String> result = list.stream()
                .filter(person -> person.getAge() > 19)
                // peek()先不用关，它不会影响整个流程，就是打印看看filter操作后还剩下什么元素
                .peek(person -> System.out.println(person.getName()))
                .skip(1)
                .limit(2)
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(result);
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


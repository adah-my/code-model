package xiaoce.middle.basal.streamapi.basic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDistinctTest
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
     * 去重
     * @param args
     */
    public static void main(String[] args)
    {
        // distinct()根据hashCode() 和 equals() 方法比较，比较对象时要实现这两个方法
        List<String> distinctList = list.stream().map(Person::getAddress).distinct().collect(Collectors.toList());
        System.out.println(distinctList);

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


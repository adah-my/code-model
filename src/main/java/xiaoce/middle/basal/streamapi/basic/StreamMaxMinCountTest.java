package xiaoce.middle.basal.streamapi.basic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamMaxMinCountTest
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
        // 匿名内部类的方式，实现Comparator，明确按什么规则比较（所谓最大必然是在某种规则下的最值）

        // max
        Optional<Integer> maxAge = list.stream().map(Person::getAge).max(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return o1 - o2;
            }
        });
        System.out.println(maxAge.orElse(0));

        Optional<Integer> max = list.stream().map(Person::getAge).max(Integer::compareTo);
        System.out.println(max.orElse(0));

        // min
        Optional<Integer> minAge = list.stream().map(Person::getAge).min(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return o1 - o2;
            }
        });
        System.out.println(minAge.orElse(0));

        Optional<Integer> min = list.stream().map(Person::getAge).min(Integer::compareTo);
        System.out.println(min.orElse(0));

        // count
        long count = list.stream().filter(person -> person.getAge() > 18).count();
        System.out.println(count);

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


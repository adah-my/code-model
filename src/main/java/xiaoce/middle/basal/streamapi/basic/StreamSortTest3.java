package xiaoce.middle.basal.streamapi.basic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSortTest3
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
        // JDK8之前：Collections工具类+匿名内部类。Collections类似一Arrays工具类，我经常用Arrays.asList()
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        // JDK8之前：List本身也实现了sort()
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge()-o2.getAge();
            }
        });

        // JDK8之后：Lambda传参给Comparator接口，其实就是实现了Comparator#compare()。注意equals是Object的，不妨碍
        list.sort((p1, p2) -> p1.getName().length()-p2.getName().length());

        // JDK8之后：使用JDK1.8为Comparator接口新增的comparing()方法
        list.sort(Comparator.comparingInt(p -> p.getName().length()));

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


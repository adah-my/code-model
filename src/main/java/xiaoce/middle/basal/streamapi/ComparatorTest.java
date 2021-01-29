package xiaoce.middle.basal.streamapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComparatorTest {

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, 170));
        list.add(new Person("am", 19, 180));
        list.add(new Person("am", 20, 180));
        list.add(new Person("iron", 19,  181));
        list.add(new Person("iron", 19,  179));
        list.add(new Person("man", 17,  160));
        list.add(new Person("man", 16,  160));
    }

    public static void main(String[] args) {
        // 先按身高降序，再按年龄降序
        list.sort(Comparator.comparingInt(Person::getHeight).thenComparingInt(Person::getAge).reversed());
        System.out.println(list);

        List<Person> sorted1 = list.stream()
                .sorted(
                        Comparator.comparing(Person::getHeight, Comparator.reverseOrder()).thenComparing(Person::getAge, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        System.out.println(sorted1);

        // 先按身高升序，再按年龄升序
        list.sort(Comparator.comparingInt(Person::getHeight).thenComparingInt(Person::getAge));
        System.out.println(list);
        List<Person> sorted2 = list.stream()
                .sorted(
                        Comparator.comparing(Person::getHeight).thenComparing(Person::getAge))
                .collect(Collectors.toList());
        System.out.println(sorted2);

        // 先按身高降序，再按年龄升序
        list.sort(Comparator.comparingInt(Person::getHeight).reversed().thenComparingInt(Person::getAge));
        System.out.println(list);

        List<Person> sorted3 = list.stream()
                .sorted(Comparator.comparing(Person::getHeight, Comparator.reverseOrder()).thenComparing(Person::getAge))
                .collect(Collectors.toList());
        System.out.println(sorted3);

        // 先按身高升序，再按年龄降序
        list.sort(Comparator.comparingInt(Person::getHeight).thenComparing(Person::getAge, Comparator.reverseOrder()));
        System.out.println(list);
        List<Person> sorted4 = list.stream()
                .sorted(Comparator.comparing(Person::getHeight).thenComparing(Person::getAge, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        System.out.println(sorted4);

        /**
         * 大家可以理解为Comparator要实现排序可以有两种方式：
         * 1、comparingInt(keyExtractor)、comparingLong(keyExtractor)... + reversed()表示倒序，默认正序
         * 2、comparing(keyExtractor, Comparator.reverseOrder())，不传Comparator.reverseOrder()表示正序
         *
         * 第四个需求如果采用reversed()，似乎达不到效果，反正我没查到。
         * 个人建议，单个简单的排序，无论正序倒序，可以使用第一种，简单一些。但如果涉及多个联合排序，建议使用第二种，语义明确不易搞错。
         *
         * 最后，上面是直接使用Collection的sort()方法，请大家自行改成Stream中的sorted()实现一遍。
         */

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person {
        private String name;
        private Integer age;
        private Integer height;

        @Override
        public String toString(){
            return "age="+age+",h="+height;
        }
    }
}
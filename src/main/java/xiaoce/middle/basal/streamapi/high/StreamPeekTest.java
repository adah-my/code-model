package xiaoce.middle.basal.streamapi.high;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPeekTest
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
     * Stream<T> peek(Comsumer<? extends T> action)
     * 它接收一个comsumer，一般有两种用法
     * 1.设置值 2.观察值
     * @param args
     */
    public static void main(String[] args)
    {
        list.stream().peek(person -> person.setAge(18)).forEach(System.out::println);

        // 观察结果我们发现，stream流中每个元素似乎是一个一个的通过stream流的
        Stream<Integer> stream = Stream.of(1, 2, 3);
        stream.peek(v -> System.out.print(v+",")).map(value -> value+100).peek(v -> System.out.print(v+",")).forEach(System.out::println);

        // 确认元素是逐个通过的
        Stream.of(1,2,3,4)
                .peek(v -> System.out.print(v+","))
                .filter(v -> v >= 2)
                .peek(v -> System.out.print(v+","))
                .filter(v -> v>=3)
                .forEach(System.out::println);

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


package xiaoce.middle.basal.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MockStream
{
    public static void main(String[] args) throws JsonProcessingException
    {
        MyList<Person> personMyList = new MyList<>();
        personMyList.add(new Person("李健", 46));
        personMyList.add(new Person("胡歌", 24));
        personMyList.add(new Person("周杰伦", 78));

        // 需求: 过滤出年龄大于40的歌手的名字
        MyList<String> result = personMyList.filter(person -> person.getAge() > 40).map(Person::getName);
        prettyPrint(result);

        System.out.println("\n------------------------------------------\n");

        // 对比真正的Stream API
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("李健", 46));
        list.add(new Person("胡歌", 24));
        list.add(new Person("周杰伦", 78));

        List<String> collect = list.stream().filter(person -> person.getAge() > 40).map(Person::getName).collect(Collectors.toList());
        prettyPrint(collect);

    }

    private static void prettyPrint(Object obj) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        System.out.println(json);
    }

}

@Data
@AllArgsConstructor
class Person{
    private String name;
    private Integer age;
}

@Getter
class MyList<T>{
    private List<T> list = new ArrayList<>();

    public boolean add(T t){
        return list.add(t);
    }

    /**
     * 给MyList传递具体的判断规则，然后MyList把内部现有符合判断（true）的元素集返回
     * @param predicate
     * @return
     */
    public MyList<T> filter(Predicate<T> predicate){
        MyList<T> filterList = new MyList<>();

        for (T t : list){
            if (predicate.test(t)){
                // 收集判断为true的元素
                filterList.add(t);
            }
        }
        return filterList;
    }

    /**
     * 把MyList中的List<T>转换为List<R>
     * @param mapper
     * @param <R>
     * @return
     */
    public <R> MyList<R> map(Function<T, R> mapper){
        MyList<R> mappedList = new MyList<>();

        for (T t : list){
            mappedList.add(mapper.apply(t));
        }

        return mappedList;
    }
    
}
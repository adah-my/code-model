package xiaoce.middle.basal.streamapi.high;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectTest3
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
     * 统计
     * @param args
     */
    public static void main(String[] args)
    {
        // 平均年龄
        Double averageAge = list.stream().collect(Collectors.averagingInt(Person::getAge));
        System.out.println(averageAge);

        // 平均薪资
        Double averageSalary = list.stream().collect(Collectors.averagingDouble(Person::getSalary));
        System.out.println(averageSalary);

        // 其他的不演示了，看api的提示即可，就是返回某个纬度的统计结果

        // 有个更绝的，针对某项数据，一次性返回多个纬度的统计结果：总和、平均数、最大值、总数，但一般用的很少
        IntSummaryStatistics allSummaryData = list.stream().collect(Collectors.summarizingInt(Person::getAge));
        long sum = allSummaryData.getSum();
        double average = allSummaryData.getAverage();
        int max = allSummaryData.getMax();
        int min = allSummaryData.getMin();
        long count = allSummaryData.getCount();
        System.out.println(allSummaryData);
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


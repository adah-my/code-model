package xiaoce.middle.basal.streamapi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapTest {
    /**
     * 需求：
     * 1.要求返回所有的key，格式为 list<Long>      提示:keyset
     * 2.要求最终返回所有value，格式为 List<Long>   提示:flatMap()，Function需要啥你就转成啥
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<Long, List<Long>> map = new HashMap<>();
        map.put(1L, new ArrayList<>(Arrays.asList(1L, 2L, 3L)));
        map.put(2L, new ArrayList<>(Arrays.asList(4L, 5L, 6L)));

        List<Long> keysList = new ArrayList<>(map.keySet());
        System.out.println(keysList);
        
        List<Long> values = map.entrySet().stream()
                .flatMap((Function<Map.Entry<Long, List<Long>>, Stream<?>>) entry -> entry.getValue().stream())
                .map(l -> Long.valueOf(String.valueOf(l)))
                .collect(Collectors.toList());
        List<Long> collect = map.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(values);
    }
}

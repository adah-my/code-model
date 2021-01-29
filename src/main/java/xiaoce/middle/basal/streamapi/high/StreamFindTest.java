package xiaoce.middle.basal.streamapi.high;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamFindTest
{

    /**
     * 查找/匹配
     * @param args
     */
    public static void main(String[] args)
    {
        // findFirst
        // 只需要第一个元素后续元素不会继续便利
        Optional<Integer> first = Stream.of(1, 2, 3, 4).peek(v -> System.out.print(v + ",")).findFirst();
//        System.out.println(first.get());

        // findAny
        // 正常流与findFirst一致，如果是并行流parallelStream结果可能会不一致
        Optional<Integer> any = Stream.of(1, 2, 3, 4).peek(v -> System.out.print(v + ",")).findAny();

        /**
         * allMatch（期望：所有元素都满足）
         * 由于是要allMatch，第一个就不符合，那么其他元素也就没有必要测试了。这是一个短路操作
         * 就好比：
         * if（0>1 && 2>1）{
         *     // 2>1 不会被执行，因为0>1不成立，所以2>1被短路了
         * }
         */
        boolean allMatch = Stream.of(1, 2, 3, 4).peek(v -> System.out.print(v + ",")).allMatch(v -> v > 2);
        System.out.println(allMatch);

        /**
         * noneMatch (期望：所有都不满足)
         * 2>=2已经false，后面元素即使都大于2也不影响最终结果：noneMatch=false，所以也是个短路操作。
         */
        boolean noneMatch = Stream.of(1, 2, 3, 4).peek(v -> System.out.print(v + ",")).noneMatch(v -> v >= 2);
        System.out.println(noneMatch);

    }

}


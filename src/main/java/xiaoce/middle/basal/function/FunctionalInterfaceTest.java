package xiaoce.middle.basal.function;

import java.util.Collection;
import java.util.function.Function;

public class FunctionalInterfaceTest
{
    public static void main(String[] args)
    {
        /**
         * 给函数式接口赋值的格式：
         * 函数式接口 = 入参 -> 出参/制造出参的语句
         */
        Consumer interface1 = str -> System.out.println(str);
        Supplier interface2 = () -> "abc";
        FunctionDemo interface3 = str -> 1;
        Predicate interface4 = str -> str.length() > 3;
    }
}

/**
 * 消费型接口，吃葡萄不吐葡萄皮：有入参，无返回值
 * (T t) -> {}
 */
interface Consumer{
    void accept(String str);
}

/**
 * 供给型接口：无中生有：没有入参，却有返回值
 * () -> T t
 */
interface Supplier{
    String get();
}

/**
 * 映射型：转换器：把 T 转换成 R
 * T t -> R r
 */
interface FunctionDemo{
    int apply(String str);
}

/**
 * 特殊映射型：把 T 转换为 boolean
 * T t -> boolean
 */
interface Predicate{
    boolean test(String str);
}


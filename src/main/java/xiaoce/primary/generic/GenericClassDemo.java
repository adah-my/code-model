package xiaoce.primary.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class GenericClassDemo
{
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        ArrayList<String> strs = new ArrayList<>();
        strs.add("aaa");
        strs.add("bbb");

        // 泛型约束只在编译期，底层仍是object
        Method method = strs.getClass().getMethod("add", Object.class);
        method.invoke(strs, 123);

        for (Object obj : strs){
            System.out.println(obj);
        }
    }
}

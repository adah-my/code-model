package xiaoce.primary.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestMethodAnnotation {

    @Override
    @MyMethodAnnotation(title = "toStringMethod", description = "override toString metod")
    public String toString() {
        return "Override toString method";
    }

    @Deprecated
    @MyMethodAnnotation(title = "old static method", description = "deprecated old static method")
    public static void oldMethod(){
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings({"unchecked"})
    @MyMethodAnnotation(title = "test method", description = "suppress warning static method")
    public static void genericsTest(){
        List l = new ArrayList<>();
        l.add("abc");
        oldMethod();
    }

    public static void main(String[] args)
    {
        try
        {
            // 获取所有 methods
            Method[] methods = TestMethodAnnotation.class.getClassLoader()
                    .loadClass("xiaoce.primary.test.TestMethodAnnotation")
                    .getMethods();
            // 遍历
            for (Method method : methods)
            {
                // 方法上是否有 MyMethodAnnotation 注解
                if (method.isAnnotationPresent(MyMethodAnnotation.class)){
                    // 遍历获取方法上所有注解
                    for (Annotation anno : method.getDeclaredAnnotations())
                    {
                        System.out.println("Annotation in Method '" + method + "' : " + anno);
                    }

                    // 获取 MyMethodAnnotation 对象信息
                    MyMethodAnnotation methodAnno = method.getAnnotation(MyMethodAnnotation.class);

                    System.out.println(methodAnno.title());
                }
            }
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}

package xiaoce.primary.annotation.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MyJunitFrameWork
{
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException
    {
        // 1.获取实例
        Class<EmplyeeDaoTest> clazz = EmplyeeDaoTest.class;
        EmplyeeDaoTest obj = clazz.newInstance();

        // 2.获取EmplyeeDaoTest类中所有公共方法
        Method[] methods = clazz.getMethods();

        // 3.迭代每一个method对象 判断哪些方法上使用了哪些注解
        ArrayList<Method> myBefores = new ArrayList<>();
        ArrayList<Method> myAfters = new ArrayList<>();
        ArrayList<Method> myTests = new ArrayList<>();
        for (Method method : methods){
            if (method.isAnnotationPresent(MyBefore.class)){
                myBefores.add(method);
            }else if (method.isAnnotationPresent(MyTest.class)){
                myTests.add(method);
            }else if (method.isAnnotationPresent(MyAfter.class)){
                myAfters.add(method);
            }
        }

        for (Method testMethod : myTests){
            for (Method beforeMethod : myBefores){
                beforeMethod.invoke(obj);
            }
            testMethod.invoke(obj);
            for (Method afterMethod : myAfters){
                afterMethod.invoke(obj);
            }
        }
    }
}

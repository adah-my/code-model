package xiaoce.primary.proxy.staticproxy;


import java.lang.reflect.*;

public class TestDynamic
{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        Calculator target = new Calculator();

        Calculation calculation = (Calculation) getProxy(target);
        System.out.println(calculation.getClass().getName());
        System.out.println(calculation instanceof Calculation);

        System.out.println(calculation.add(1,2));
        System.out.println(calculation.sub(1,2));
    }

    private static Object getProxy(Object target) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        // proxyClass 为 com.sun.xiaoce.primary.proxy.$Proxy0
        Class proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
        Constructor constructor = proxyClass.getConstructor(InvocationHandler.class);
        // obj 为代理对象
        Object obj = constructor.newInstance(new InvocationHandler()
        {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
                System.out.println(method.getName()+"执行开始...");
                Object invoke = method.invoke(target, args);
                System.out.println(method.getName()+"执行结束...");
                return invoke;
            }
        });
        return obj;
    }


    public static Object getProxyInstance(Object target){
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler()
        {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
                System.out.println("开始");
                Object invoke = method.invoke(target, args);
                System.out.println("结束");
                return invoke;
            }
        });
        return o;
    }

}

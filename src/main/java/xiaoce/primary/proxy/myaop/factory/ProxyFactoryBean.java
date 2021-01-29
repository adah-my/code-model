package xiaoce.primary.proxy.myaop.factory;

import xiaoce.primary.proxy.myaop.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactoryBean
{
    // 通知
    private TransactionManager txManager;
    // 目标对象
    private Object target;

    public void setTxManager(TransactionManager txManager){
        this.txManager = txManager;
    }

    public void setTarget(Object target){
        this.target = target;
    }

    // 传入目标对象target， 为它装配好通知，返回代理对象
    public Object getProxy(){
        Object proxy = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler()
                {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                    {
                        try
                        {
                            // 1.开启事务
                            txManager.beginTransaction();
                            // 2.执行操作
                            Object result = method.invoke(target, args);
                            // 3.提交事务
                            txManager.commit();
                            // 4.返回结果
                            return result;
                        } catch (Exception e)
                        {
                            // 5.回滚事务
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally
                        {
                            // 6.释放连接
                            txManager.release();
                        }
                    }
                });
        return proxy;
    }

}

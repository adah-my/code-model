package xiaoce.primary.proxy.myaop;

import xiaoce.primary.proxy.myaop.factory.BeanFactory;

public class AOPTest
{
    public static void main(String[] args)
    {
        try
        {
            BeanFactory beanFactory = new BeanFactory();
            Object bean = beanFactory.getBean("xiaoce.primary.proxy.myaop.service.impl.UserServiceImpl");
            System.out.println(bean.getClass().getName());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}

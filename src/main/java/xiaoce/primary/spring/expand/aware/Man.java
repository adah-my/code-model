package xiaoce.primary.spring.expand.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Man implements DisposableBean, InitializingBean,
        ApplicationContextAware,
        BeanNameAware,
        BeanFactoryAware,
        EnvironmentAware,
        ApplicationEventPublisherAware
{
    private String name;
    private int age;
    private String beanName;

    public Man(String name, int age)
    {
        System.out.println("Man 带参构造执行...");
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public void destroy() throws Exception
    {
        System.out.println("Man继承自 DisposableBean 接口的 destroy 方法执行...");

    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        System.out.println("Man继承自 InitializingBean 接口的 afterPropertiesSet 方法执行...");
        
    }

    public void init(){
        System.out.println("Man通过 @Bean 的 init-method 指定的方法init方法执行...");
    }
    
    public void destroySelf(){
        System.out.println("Man通过 @Bean 的 destroymethod 指定的方法destroySelf方法执行...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Man 类通过@PostConstruct注解指定的PostConstruct方法执行........");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Man 类通过@PreDestroy注解指定的PreDestroy方法执行......");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        System.out.println("Man继承自 ApplicationContextAware 接口的 setApplicationContext 方法执行...");
    }

    @Override
    public void setBeanName(String name)
    {
        this.beanName = name;
        System.out.println("Man继承自 BeanNameAware 接口的 setBeanName 方法执行..." + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException
    {
        System.out.println("Man继承自 BeanFactoryAware 接口的 setBeanFactory 方法执行..." + beanFactory.containsBean(beanName));
    }


    @Override
    public void setEnvironment(Environment environment)
    {
        System.out.println("Man继承自 EnvironmentAware 接口的 setEnvironment 方法执行..." + environment.toString());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher)
    {
        System.out.println("Man继承自 ApplicationEventPublisherAware 接口的 setApplicationEventPublisher 方法执行..." + applicationEventPublisher.toString());
    }

}

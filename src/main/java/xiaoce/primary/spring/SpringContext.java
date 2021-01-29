package xiaoce.primary.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext
{
    private static ApplicationContext APP_CONTEXT;

    public SpringContext() {
    }


    public static ApplicationContext appContext() {
        return APP_CONTEXT;
    }

    public static void start(){

        String[] paths = new String[]{"engine/*Context.xml", "context/*Context.xml"};

        APP_CONTEXT = new ClassPathXmlApplicationContext(paths);
    }


}

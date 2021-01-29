package xiaoce.primary.spring.expand.aware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
        ((AnnotationConfigApplicationContext)app).close();
    }
}

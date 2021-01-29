package xiaoce.primary.spring.expand.aware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("xiaoce.primary.spring.expand.aware")
@PropertySource(value = "classpath:/application.properties")
public class MainConfig
{
    @Bean(value="man", initMethod = "init", destroyMethod = "destroySelf")
    public Man man(){
        return new Man("paper", 20);
    }
}

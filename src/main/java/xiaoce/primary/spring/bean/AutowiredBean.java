package xiaoce.primary.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class AutowiredBean
{
    public String show(){
        return "hello xiaoce.primary.spring";
    }
}

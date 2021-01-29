package xiaoce.primary;


import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

public class Test
{
    public static void main(String[] args)
    {

    }


}

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface TestInheritedAnnotation {
    String[] values();
    int number();
}

@TestInheritedAnnotation(values = {"value"}, number = 10)
class Person {
}
class Student extends Person {
    public void test() {
        
    }
}


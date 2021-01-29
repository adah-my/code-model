package xiaoce.primary.annotation;

@MyAnnotation(value = "testBean")
public class TestBean
{
    @MyAnnotation("field")
    public String name;

    @MyAnnotation(value = "show")
    public void show(){
        System.out.println("aaaa");
    }

    @MyAnnotation()
    public void defaultMethod(){
        System.out.println("test");
    }
}

package xiaoce.primary.annotation;


public class TestAnnotation
{
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException
    {
        Class<TestBean> clazz = TestBean.class;
        MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.value());

        MyAnnotation name = clazz.getField("name").getAnnotation(MyAnnotation.class);
        System.out.println(name.value());

        MyAnnotation show = clazz.getMethod("show", null).getAnnotation(MyAnnotation.class);
        System.out.println(show.value());

        MyAnnotation defaultMethod = clazz.getMethod("defaultMethod", null).getAnnotation(MyAnnotation.class);
        System.out.println(defaultMethod.value());

    }
}

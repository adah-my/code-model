package xiaoce.primary.spring;


import xiaoce.primary.spring.bean.Person;
import xiaoce.primary.spring.bean.Student;

public class StartUp
{


    public static void main(String[] args)
    {
        SpringContext.start();
        Student student = (Student) SpringContext.appContext().getBean("student");
        Person person = (Person) SpringContext.appContext().getBean("person");

        System.out.println(student);
        System.out.println(person);

    }
}

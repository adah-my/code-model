package xiaoce.primary.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class Person
{
    private String name;
    private Integer age;

    @Autowired
    private AutowiredBean autowiredBean;

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
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'+autowiredBean.show();
    }
}

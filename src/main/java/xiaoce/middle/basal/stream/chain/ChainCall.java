package xiaoce.middle.basal.stream.chain;

public class ChainCall
{
    public static void main(String[] args)
    {
        Person person = new Person().setAge(12).setName("adf").setMoney(123.1);
    }
}

class Person{
    private String name;
    private Integer age;
    private Double money;

    public String getName()
    {
        return name;
    }

    public Integer getAge()
    {
        return age;
    }

    public Double getMoney()
    {
        return money;
    }

    public Person setName(String name)
    {
        this.name = name;
        return this;
    }

    public Person setAge(Integer age)
    {
        this.age = age;
        return this;
    }

    public Person setMoney(Double money)
    {
        this.money = money;
        return this;
    }
}

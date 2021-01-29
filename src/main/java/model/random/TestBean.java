package model.random;

public class TestBean
{
    private String id;
    private Integer weight;

    public TestBean(String id, Integer weight)
    {
        this.id = id;
        this.weight = weight;
    }

    public TestBean()
    {
    }

    public Integer getWeight()
    {
        return weight;
    }

    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "TestBean{" +
                "id='" + id + '\'' +
                ", weight=" + weight +
                '}';
    }
}

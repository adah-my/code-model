package xiaoce.primary.spring.bean;

public class Student
{
    private String school;
    private Integer level;

    public String getSchool()
    {
        return school;
    }

    public void setSchool(String school)
    {
        this.school = school;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    @Override
    public String toString()
    {
        return "Student{" +
                "school='" + school + '\'' +
                ", level=" + level +
                '}';
    }
}

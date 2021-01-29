package xiaoce.primary.annotation.jpa;

public class TestUserDao
{
    public static void main(String[] args)
    {
        UserDao userDao = new UserDao();
        User user = new User("muyi", 20);
        userDao.add(user);
    }
}

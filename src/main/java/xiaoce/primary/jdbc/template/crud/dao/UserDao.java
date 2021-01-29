package xiaoce.primary.jdbc.template.crud.dao;

import xiaoce.primary.jdbc.template.crud.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao
{
    int addUser(User user);
    int update(User user);
    int delete(int userId);
    User getUser(int userId);
    User findUser(String name, int age);

    List<User> selectUser(int age);
}

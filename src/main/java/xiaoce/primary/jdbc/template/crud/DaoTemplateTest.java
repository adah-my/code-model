package xiaoce.primary.jdbc.template.crud;

import xiaoce.primary.jdbc.template.crud.dao.UserDao;
import xiaoce.primary.jdbc.template.crud.dao.UserDaoImpl;
import xiaoce.primary.jdbc.template.crud.pojo.User;

import java.util.Date;
import java.util.List;

public class DaoTemplateTest
{
    public static void main(String[] args)
    {
        UserDao userDao = new UserDaoImpl();

        User user = new User();
        user.setName("asd");
        user.setAge(20);
        user.setBirthday(new Date());

//        userDao.addUser(user);

//        user.setId(4);
//        userDao.update(user);

//        userDao.delete(5);

//        User asd = userDao.findUser("asd", 20);
//        System.out.println(asd);
//
//        User user1 = userDao.getUser(1);
//        System.out.println(user1);

        List<User> users = userDao.selectUser(20);
        users.forEach(System.out::println);

    }
}

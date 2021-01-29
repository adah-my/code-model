package xiaoce.primary.jdbc.template.crud;

import xiaoce.primary.jdbc.best.DaoFactory;
import xiaoce.primary.jdbc.template.crud.dao.UserDao;
import xiaoce.primary.jdbc.template.crud.dao.UserDaoJdbcImpl;
import xiaoce.primary.jdbc.template.crud.pojo.User;

import java.sql.SQLException;
import java.util.Date;

public class DAOTest
{
    public static void main(String[] args) throws SQLException
    {
//        UserDao userDao = new UserDaoJdbcImpl();

//        User user = new User();
//        user.setName("aa");
//        user.setAge(2);
//        user.setBirthday(new Date());
//
//        userDao.addUser(user);
//
//        user.setId(2);
//        user.setName("bbb");
//        user.setAge(22);
//        userDao.update(user);

//        userDao.delete(2);
//
//        User user = userDao.getUser(3);
//        System.out.println(user);

        UserDao userDao = DaoFactory.getInstance().getUserDao();

        User muyi = userDao.findUser("muyi", 20);
        System.out.println(muyi);
    }
}

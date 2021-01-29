package xiaoce.primary.jdbc.best;

import xiaoce.primary.jdbc.template.crud.dao.UserDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory
{
    private static UserDao userDao = null;
    private static DaoFactory daoFactory = new DaoFactory();

    private DaoFactory(){
        try
        {
            Properties properties = new Properties();
            InputStream inputStream = DaoFactory.class.getClassLoader().getResourceAsStream("jdbc/daoconfig.properties");
            properties.load(inputStream);

            String userDaoClass = properties.getProperty("userDao");
            Class<?> userDaoImplClazz = Class.forName(userDaoClass);
            // 反射创建对象
            userDao = (UserDao)userDaoImplClazz.newInstance();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DaoFactory getInstance(){
        return daoFactory;
    }

    public UserDao getUserDao(){
        return userDao;
    }

}

package xiaoce.primary.jdbc.driver;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DriverDemo
{
    @Test
    public void testDriver() throws SQLException, ClassNotFoundException, IOException
    {
//        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSL=false";
//        String username = "root";
//        String password = "root";

        // com.mysql.cj.jdbc.Driver会通过静态代码块，将自己注册到了DriverManager。


        // 一、被动加载驱动
//        // 1.创建一个 Driver 实现类对象
//        Driver driver = new com.mysql.cj.jdbc.Driver();
//
//        // 2.准备连接数据库的基本信息：url， user， password
//        Properties info = new Properties();
//        info.put("user", username);
//        info.put("password", password);
//
//        // 3.调用 Driver 接口的 connect(url,info) 获取数据库连接
//        Connection connect = driver.connect(url, info);
//        System.out.println(connect);


        // 二、主动加载驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection(url, username, password);
//        System.out.println(connection);


        // 三、代码优化 - 配置文件读取
        Properties properties = getProperties();

        Class.forName(properties.getProperty("driver"));
        String jdbcurl = properties.getProperty("jdbcurl");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        Connection connection = DriverManager.getConnection(jdbcurl, username, password);
        System.out.println(connection);

    }

    public Properties getProperties() throws IOException
    {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc/db.properties");

        properties.load(inputStream);
        return properties;
    }

}

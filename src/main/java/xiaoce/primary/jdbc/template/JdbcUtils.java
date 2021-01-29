package xiaoce.primary.jdbc.template;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils
{
    public static Connection getConnection()
    {
        Connection connection = null;
        try
        {
            Properties properties = getProperties();

            String url = properties.getProperty("jdbcurl");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String driver = properties.getProperty("driver");

            // 1.注册驱动
            Class.forName(driver);

            // 2.建立连接
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }

    public static Properties getProperties()
    {
        Properties properties = null;
        try
        {
            properties = new Properties();
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc/db.properties");

            properties.load(inputStream);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return properties;
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn)
    {
        try
        {
            if (rs != null){
                rs.close();
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }finally
        {
            try
            {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }finally
            {
                try
                {
                    if (conn != null){
                        conn.close();
                    }
                } catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }
            }
        }
    }
}

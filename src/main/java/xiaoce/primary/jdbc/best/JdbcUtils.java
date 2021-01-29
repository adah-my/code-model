package xiaoce.primary.jdbc.best;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Jdbc结合数据源
 */
public class JdbcUtils
{
    private static MyDataSource dataSource = new MyDataSource();

    public static Connection getConnection() throws SQLException
    {
       return dataSource.getConnection();
    }

    public static MyDataSource getDataSource()
    {
        return dataSource;
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

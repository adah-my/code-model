package xiaoce.primary.proxy.myaop;


import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;

public class ConnectionUtils
{
    private ThreadLocal<Connection> tl = new ThreadLocal<>();

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection(){
        try
        {
            // 1.先从ThreadLocal中获取
            Connection conn = tl.get();
            // 2.判断当前线程上是否有连接
            if (conn == null){
                // 3.从数据源中获取一个连接，并且存放如ThreadLocal中
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            // 4.返回当前线程上的连接
            return conn;
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }
}

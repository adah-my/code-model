package xiaoce.primary.jdbc.best;

import xiaoce.primary.jdbc.template.JdbcUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

public class MyDataSource
{

    private static String url;
    private static String username;
    private static String password;

    static {
        Properties properties = JdbcUtils.getProperties();
        url = properties.getProperty("jdbcurl");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    /**
     * 数据源（DataSource）：负责生产连接，它内部有一个连接池
     * 连接池（ConnectionsPool）：它只是个容器，用于存放数据源生成的连接，本身不能创建连接
     * 空闲连接（Idle Connection）：在连接池中的连接。用户从池中拿走，正在使用的是”忙碌“的连接
     * initCount：new MyDataSource()时，往池中预先存入initCount个连接（也算空闲连接）
     * maxIdleCount：连接池最大空闲连接数。和数据源能产生多少个连接无关
     *              连接池最多能存10个，但是数据源可以生产第11个。第一个无法归还池中？无所谓，直接销毁
     * currentIdleCount：当前存活的连接数（池中空闲+用户拿去的）
     */

    // 池中初始连接数
    private static int initCount = 5;
    // 池中最小连接数，小于这个数量就要创建连接并加入池中
    private static int minIdleCount = 3;
    // 池中最大允许存放的连接数
    private static int maxIdleCount = 10;
    // 当前池中的连接数
    private static int currentIdleCount = 0;
    // 数据源创建链接的次数
    private static int createCount = 0;

    private final static LinkedList<Connection> connectionsPool = new LinkedList<>();

    /**
     * 空参构造，按照initCount预先创建一定数量的连接存入池中
     */
    public MyDataSource(){
        try
        {
            for (int i = 0; i < initCount; i++)
            {
                // 创建RealConnection
                Connection realConnection = DriverManager.getConnection(url, username, password);
                // 将RealConnection传入createProxyConnection()，得到代理连接并加入池中，currentIdleCount++
                connectionsPool.addLast(this.createProxyConnection(realConnection));
                currentIdleCount++;
            }
            System.out.println("-----------连接池初始化结束，共初始化" + currentIdleCount + "个Connection-----------");
        } catch (SQLException e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 公共方法，外界通过MyDataSOurce调用此方法得到代理连接
     */
    public Connection getConnection() throws SQLException
    {

        // 同步代码
        synchronized (connectionsPool){
            // 连接池中还有空闲连接，从池中取出，currnetIdleCount--
            if (currentIdleCount > 0){
                currentIdleCount--;
                if (currentIdleCount < minIdleCount){
                    // 创建RealConnection
                    Connection realConnection = DriverManager.getConnection(url, username, password);
                    // 将RealConnection传入createProxyConnection(),得到代理连接并加入池中，currentIdleCount++
                    connectionsPool.addLast(this.createProxyConnection(realConnection));
                    currentIdleCount++;
                }
                return connectionsPool.removeFirst();
            }

            /**
             * 如果连接池中没有空闲连接（都被用户拿走了），那么在生成连接，比如第11个
             * 不用考虑maxIdleCount，它指的是连接池最多存放多少个空闲连接，而不是数据源能生成多少个
             * 如果这第11连接后期调用close，程序会判断当前连接池中的连接数是否大于maxIdleCount
             * 如果已经存满就直接销毁第11个连接，不会放入池中
             */
            Connection realConnection = DriverManager.getConnection(url, username, password);
            return this.createProxyConnection(realConnection);
        }
    }


    /**
     * 用于生成代理连接
     * @param realConn
     * @return
     */
    private Connection createProxyConnection(Connection realConn)
    {
        // 这句代码仅仅是为了把realConn转为final，这样才能在匿名对象invocationHandler中使用
        final Connection realConnection = realConn;

        // 动态代理：返回Connection代理对象
        Connection proxyConnection = (Connection)Proxy.newProxyInstance(realConn.getClass().getClassLoader(), realConn.getClass().getInterfaces(), new InvocationHandler()
        {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
                // 对close方法进行拦截
                if ("close".equals(method.getName()))
                {
                    // 当连接池空闲连接数小于最大空闲数，说明还能存得下，于是连接被归还到池中
                    if (MyDataSource.currentIdleCount < MyDataSource.maxIdleCount)
                    {
                        MyDataSource.connectionsPool.addLast((Connection) proxy);
                        MyDataSource.currentIdleCount++;
                        return 1;
                    } else
                    {
                        // 当连接池满了，这个连接已经村放不下，所以只能销毁
                        realConnection.close();
                        return 1;
                    }
                }
                return method.invoke(realConnection, args);
            }
        });
        System.out.println("新建Connection(" + (++MyDataSource.createCount) + ")：" + proxyConnection);
        return proxyConnection;
    }

}

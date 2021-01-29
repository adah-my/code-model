package xiaoce.primary.proxy.myaop;

import java.sql.SQLException;

/**
 * AOP通知（事务管理器）
 */
public class TransactionManager
{
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils){
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction(){
        try
        {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit(){
        try
        {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback(){
        try
        {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    public void release(){
        try
        {
            connectionUtils.getThreadConnection().close();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

}

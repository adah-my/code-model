package xiaoce.primary.jdbc.driver;

import org.junit.Test;
import xiaoce.primary.jdbc.template.JdbcUtils;

import java.sql.*;

public class Basic
{
    @Test
    public void testJdbc()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try
        {
            connection = JdbcUtils.getConnection();

            // 3.创建sql模板
            String sql = "select * from t_user where id = ?";
            preparedStatement = connection.prepareStatement(sql);

            // 4.设置模板参数
            preparedStatement.setInt(1, 0);

            // 5.执行语句
            rs = preparedStatement.executeQuery();

            // 6.获取返回参数
            while (rs.next()){
                System.out.println(rs.getInt("id") + " " +
                        rs.getString("name") + " " +
                        rs.getInt("age") + " " +
                        rs.getDate("birthday"));
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        } finally
        {
            // 7.释放连接
            JdbcUtils.close(rs, preparedStatement, connection);

        }


    }


}

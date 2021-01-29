package xiaoce.primary.jdbc.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public class MyDataSoutceTest
{
    public static void main(String[] args) throws SQLException
    {
        MyDataSource dataSource = new MyDataSource();
        for (int i = 0; i < 20; i++)
        {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            connection.close();
        }
    }
}

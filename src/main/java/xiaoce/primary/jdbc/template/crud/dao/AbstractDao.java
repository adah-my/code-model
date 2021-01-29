package xiaoce.primary.jdbc.template.crud.dao;

//import xiaoce.primary.jdbc.template.JdbcUtils;  // jdbc1.0
import xiaoce.primary.jdbc.best.JdbcUtils;  // 测试jdbc2.0
import xiaoce.primary.jdbc.template.crud.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao
{

    // 增删改
    public int update(String sql, Object... args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1, args[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e);
        } finally
        {
            JdbcUtils.close(rs, ps, conn);
        }
    }

    // 查
    public List<Object> query(String sql, Object[] args, RowMapper rowMapper){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try
        {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();

            while (rs.next()){
                Object obj = rowMapper(rs);
                list.add(obj);
            }
            return list;
        } catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e);
        } finally
        {
            JdbcUtils.close(rs, ps, conn);
        }
    }

    abstract protected Object rowMapper(ResultSet rs);
}

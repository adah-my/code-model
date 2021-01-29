package xiaoce.primary.jdbc.template.crud.dao;

import xiaoce.primary.jdbc.best.JdbcUtils;
import xiaoce.primary.jdbc.template.crud.exception.DaoException;
import xiaoce.primary.jdbc.template.crud.pojo.User;

import java.sql.*;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao
{
    @Override
    public int addUser(User user)
    {
         Connection conn = null;
         PreparedStatement ps = null;
         ResultSet rs = null;

        try
        {
            conn = JdbcUtils.getConnection();
            String sql = "insert into t_user(name,age,birthday) value (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setDate(3, new Date(user.getBirthday().getTime()));

            return ps.executeUpdate();
        }catch (SQLException e){
            throw new DaoException(e.getMessage(), e);
        }
        finally
        {
            JdbcUtils.close(rs, ps, conn);
        }
    }

    @Override
    public int update(User user)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conn = JdbcUtils.getConnection();
            String sql = "update t_user set name=?,age=?,birthday=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setDate(3, new Date(user.getBirthday().getTime()));
            ps.setInt(4, user.getId());

            return ps.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e.getMessage(), e);
        }
        finally
        {
            JdbcUtils.close(rs, ps, conn);
        }
    }

    @Override
    public int delete(int userId)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conn = JdbcUtils.getConnection();
            String sql = "delete from t_user where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            return ps.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e.getMessage(), e);
        }
        finally
        {
            JdbcUtils.close(rs, ps, conn);
        }
    }

    @Override
    public User getUser(int userId)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try
        {
            conn = JdbcUtils.getConnection();
            String sql = "select * from t_user where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            rs = ps.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setBirthday(rs.getDate("birthday"));
            }
        } catch (SQLException e){
            throw new DaoException(e.getMessage(), e);
        }
        finally
        {
            JdbcUtils.close(rs, ps, conn);
        }
        return user;
    }

    @Override
    public User findUser(String name, int age)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try
        {
            conn = JdbcUtils.getConnection();
            String sql = "select *  from t_user where name=? and age=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, age);

            rs = ps.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setBirthday(rs.getDate("birthday"));
            }
        }catch (SQLException e){
            throw new DaoException(e.getMessage(), e);
        }
        finally
        {
            JdbcUtils.close(rs, ps, conn);
        }
        return user;
    }

    @Override
    public List<User> selectUser(int age)
    {
        return null;
    }
}

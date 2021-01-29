package xiaoce.primary.jdbc.template.crud.dao;

import xiaoce.primary.jdbc.template.crud.exception.DaoException;
import xiaoce.primary.jdbc.template.crud.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao
{
    @Override
    public int addUser(User user)
    {
        String sql = "insert into t_user(name,age,birthday) value (?,?,?)";
        Object[] args = new Object[]{user.getName(), user.getAge(), user.getBirthday()};
        return super.update(sql, args);
    }

    @Override
    public int update(User user)
    {
        String sql = "update t_user set name=?,age=?,birthday=? where id=?";
        Object[] args = new Object[]{user.getName(), user.getAge(), user.getBirthday(), user.getId()};
        return super.update(sql, args);
    }

    @Override
    public int delete(int userId)
    {
        String sql = "delete from t_user where id=?";
        return super.update(sql, userId);
    }

    @Override
    public User getUser(int userId)
    {
        String sql = "select * from t_user where id=?";
        List<Object> list = super.query(sql, new Object[]{userId}, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException
            {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setBirthday(rs.getDate("birthday"));
                return user;
            }
        });
        return (User) list.get(0);
    }

    @Override
    public User findUser(String name, int age)
    {
        String sql = "select *  from t_user where name=? and age=?";
        Object[] args = new Object[]{name, age};
        List<Object> list = super.query(sql, args, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException
            {
                return rowMapper(rs);
            }
        });
        return (User)list.get(0);
    }

    @Override
    public List selectUser(int age)
    {
        String sql = "select *  from t_user where  age=?";
        Object[] args = new Object[]{age};
        List<Object> list = super.query(sql, args, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException
            {
                return rowMapper(rs);
            }
        });
        return list;
    }

    @Override
    protected Object rowMapper(ResultSet rs)
    {
        try
        {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            user.setBirthday(rs.getDate("birthday"));
            return user;
        } catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e);
        } finally
        {
        }
    }
}

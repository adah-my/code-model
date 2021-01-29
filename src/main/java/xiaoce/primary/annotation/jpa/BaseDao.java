package xiaoce.primary.annotation.jpa;

import com.alibaba.druid.pool.DruidDataSource;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.druid.DruidPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public class BaseDao<T>
{

    private static String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSL=false";
    private static String username = "root";
    private static String password = "root";



    private static DruidDataSource dataSource = new DruidDataSource();


    private Class<T> beanClass;

    public BaseDao(){
        DruidPlugin dp = new DruidPlugin(url, username, password);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);

        // 重点：手动调用start方法
        dp.start();
        arp.start();

        // 通过子类获得传给父类的泛型class对象， 假设是User.class
        beanClass = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void add(T bean){
        // 获取user对象所有的字段
        Field[] fields = beanClass.getDeclaredFields();

        // 拼接sql语句，表名直接用pojo的类名
        // 所以创建表时，写成User
        String sql = "insert into " + beanClass.getAnnotation(Table.class).value() + " values(";
        for (int i = 0; i < fields.length; i++)
        {
            sql += "?";
            if (i < fields.length - 1){
                sql += ",";
            }
        }
        sql += ")";

        // 获得bean字段的值（要插入的记录）
        ArrayList<Object> paramList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++)
        {
            try
            {
                fields[i].setAccessible(true);
                Object o = fields[i].get(bean);
                paramList.add(o);
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        int size = paramList.size();
        Object[] params = paramList.toArray(new Object[0]);

        int update = Db.update(sql, params);
        System.out.println(update);

    }
    
}

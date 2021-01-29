package xiaoce.middle.mysql;

public class ParamType
{
    /**
     * 1.整数类型
     * 数值类型唯一需要注意的3点：
     *      如果业务允许，尽量设置unsigned（无符号）
     *      int(11)里的11和占用字节大小无关
     *      注意各个类型的选取标准
     *
     * 语句书写顺序
     * SELECT ... FROM ... table WHERE ... GROUP BY ... HAVING ... ORDER BY ... LIMIT ...
     * 除了SELECT，后面几个顺序可以及以为：温哥华OL，意思时温哥华白领
     * 语句书写顺序
     * SELECT ... FROM table WHERE ... GROUP BY ... HAVING ... ORDER BY ... LIMIT ...
     * 除了SELECT，后面几个顺序可以记忆为：温哥华OL，意思是温哥华白领。
     *
     * 关联查询
     * 隐式连接
     * 格式是： FROM t_a, t_b WHERE或ON 等值连接条件
     *
     * 显式连接
     * 内连接格式：FROM t_a [INNER] JOIN t_b ON 连接条件
     *
     * 外连接格式：FROM t_a LEFT JOIN t_b ON 连接条件
     *
     * 子查询
     * 子查询指的就是在一个查询之中嵌套了其他若干个查询
     * 子查询通常出现在：
     *      WHERE后面：SELECT name FROM table_a WHERE id IN (SELECT id FROM table_b)
     *      FROM后面：SELECT name FROM (SELECT name, age FROM table_a) tem LEFT JOIN ...
     *      EXISTST后面：没用过
     *
     */
}

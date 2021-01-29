package xiaoce.primary.clone;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.io.Serializable;

public class TestClone
{
    public static void main(String[] args) throws CloneNotSupportedException
    {
        // 浅拷贝
        CloneExample cloneExample = new CloneExample();
        CloneExample clone = cloneExample.clone();
        System.out.println(clone == cloneExample);
        System.out.println(clone.getArr() == cloneExample.getArr());

        // 深拷贝
        CloneExample clone1 = SerializedClone.clone(cloneExample);
        System.out.println(clone1 == cloneExample);
        System.out.println(clone1.getArr() == cloneExample.getArr());


    }
}

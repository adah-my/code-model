package xiaoce.primary.generic;

public class Test
{
    public static void main(String[] args)
    {
        String[] strs = new String[4];
        strs[0] = "a";
        strs[1] = "a";
        strs[2] = "a";

        Object[] objs = strs;
        objs[3] = 1;
        System.out.println(objs);
    }
}

package xiaoce.middle.basal.lambda;

public class MethodAndLambdaTest
{
    public static void main(String[] args)
    {
        // 1.匿名对象
        MyPredict myPredict = new MyPredict()
        {
            @Override
            public boolean test(int a, int b)
            {
                return a - b > 0;
            }
        };
        myPredict.test(1, 2); // false

        // 2.从匿名对象过渡到Lambda表达式
        MyPredict myPredict2 = (a, b) -> a-b > 0;
        myPredict2.test(1, 2); // false

        // 3.MyInteger#compare() 的方法体和上面的Lambda表达式逻辑相同，可以直接引用
        MyPredict myPredict3 = MyInteger::compare;
        myPredict3.test(1, 2); // false

        // 4.Lambda说，你想模仿我？想得美！老子DIY一下比较规则（a减b 变成了 b减a）
        MyPredict myPredict5 = (a, b) -> b-a > 0;
        myPredict5.test(1, 2); // true

        // 5.看到这，方法不服气，也想DIY一把
        MyPredict myPredict6 = MyInteger::compare;
        // ???, 没法DIY，MyInteger::compare是把整个方法搬过来，不能修改内部的逻辑

    }

}

interface MyPredict{
    boolean test(int a, int b);
}

class MyInteger{
    public static boolean compare(int a, int b){
        return a-b > 0;
    }
}

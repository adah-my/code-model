package xiaoce.primary.proxy.staticproxy;

public class CalculatorProxy implements Calculation
{
    private Calculation target;

    public CalculatorProxy(Calculation calculation){
        target = calculation;
    }

    @Override
    public int add(int a, int b)
    {
        System.out.println("add开始..");
        int result = target.add(a, b);
        System.out.println("add结束..");
        return result;
    }

    @Override
    public int sub(int a, int b)
    {
        System.out.println("sub开始..");
        int result = target.add(a, b);
        System.out.println("sub结束..");
        return result;
    }
}

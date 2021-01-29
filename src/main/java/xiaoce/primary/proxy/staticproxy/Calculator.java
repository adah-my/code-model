package xiaoce.primary.proxy.staticproxy;

public class Calculator implements Calculation
{

    @Override
    public int add(int a, int b){
        int result = a + b;
        return result;
    }

    @Override
    public int sub(int a, int b){
        int result = a - b;
        return result;
    }
}

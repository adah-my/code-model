package xiaoce.primary.proxy.staticproxy;

public class Test
{
    public static void main(String[] args)
    {
        /**
         * 静态代理：
         * 1.直接修改源码，不符合开闭原则，应对扩展开发对修改关闭 （ √）
         * 2. 如果Calculator有几十个、上百个方法，修改量太大  ×
         * 3.存在重复代码（在核心代码前后打印日志） ×
         * 4.日志打印硬编码在代理类中，不利于后期维护：如果功能需要修改或删除，需要花费较大代价  ×
         */


        Calculation calculation = new CalculatorProxy(new Calculator());

        calculation.add(1, 2);
        calculation.sub(1, 2);
    }
}

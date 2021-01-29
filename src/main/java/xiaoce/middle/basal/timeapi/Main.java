package xiaoce.middle.basal.timeapi;

public class Main
{
    /**
     *
     * 随着Java8的发布，Oracle同时推出了一套全新的时间API，算是填坑把。旧版时间API的坑相信大家也早有耳闻
     * 1.SimpleDateFormat是线程不安全的，多线程环境下共用一个Formatter可能会抛异常
     * 2.month被设计的非常奇葩，用0~11表示1~12月，所以如果要表示12月，你需要填写11，一不小心就掉坑里
     * 3.同时存在两个Date，一个是java.util下，另一个是java.sql下，API还不同，比较混乱
     * 4.时间计算非常麻烦
     * 5.API设计太散了，散落在Data、Calendar、SimpleDateFormat三个类中，想找时找不到，干着急
     *
     */
}

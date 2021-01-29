package xiaoce.middle.basal.timeapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class NewDateApiTest
{
    /**
     * 新的时间API禁止了new的方式，全部通过工厂方式创建时间对象，其中最常用的就是now/of
     * @param args
     */
    public static void main(String[] args)
    {
        // 旧的方式
        Date date = new Date();
        System.out.println("old api"+date);

        // 新的方式，只能通过给定的方法获取
        LocalDate newDate = LocalDate.now();            // 日期 2020-12-12
        LocalTime newTime = LocalTime.now();            // 时间 16:30:00:00
        LocalDateTime newDateTime = LocalDateTime.now();// 日期+时间 2020-12-12T16:30:00:00
        System.out.println("newDate："+newDate);
        System.out.println("newTime："+newTime);
        System.out.println("newDateTime："+newDateTime);

        // 还可以组合哟
        LocalDateTime combineDateTime = LocalDateTime.of(newDate, newTime);
        System.out.println("combineDateTime："+combineDateTime);

        // 创建指定时间
        LocalDate customDate = LocalDate.of(2020, 12, 12);
        LocalTime customTime = LocalTime.of(16, 30, 0);
        LocalDateTime customDateTime = LocalDateTime.of(2020, 12, 12, 16, 30, 0);
        System.out.println("customDate："+customDate);
        System.out.println("customTime："+customTime);
        System.out.println("customDateTime："+customDateTime);
        
    }
}

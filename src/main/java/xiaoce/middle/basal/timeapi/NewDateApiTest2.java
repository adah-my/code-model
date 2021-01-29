package xiaoce.middle.basal.timeapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class NewDateApiTest2
{
    /**
     * 如何增减时间 plus/minus
     * @param args
     */
    public static void main(String[] args)
    {
        // 获取时间参数的年、月、日（有时需求要用到）
        System.out.println("获取时间参数的年、月、日：");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("year: "+now.getYear());
        System.out.println("month: "+now.getMonth());
        System.out.println("day: "+now.getDayOfMonth());
        System.out.println("hour: "+now.getHour());
        System.out.println("minute: "+now.getMinute());
        System.out.println("second: "+now.getSecond());
        System.out.println();

        // 计算昨天同一时刻（由于对象不可修改，所以返回的是新对象）
        System.out.println("计算前一天的当前时刻：");
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.plus(-1, ChronoUnit.DAYS);
        System.out.println("today: "+today);
        System.out.println("yesterday: "+yesterday);
        System.out.println("same object: "+today.equals(yesterday));
        System.out.println();

        // 计算当天的00点和24点（你看，这里就看到组合的威力了）
        System.out.println("计算当天00点和24点：");
        LocalDateTime todayBegin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        System.out.println("todayBegin: "+todayBegin);
        System.out.println("todayEnd: "+todayEnd);
        System.out.println();

        System.out.println("计算一周、一个月、一年前的当前时刻：");
        LocalDateTime oneWeekAgo = today.minus(1, ChronoUnit.WEEKS);
        LocalDateTime oneMonthAgo = today.minus(1, ChronoUnit.MONTHS);
        LocalDateTime oneYearAgo = today.minus(1, ChronoUnit.YEARS);
        System.out.println("oneWeekAge: "+oneWeekAgo);
        System.out.println("oneMonthAge: "+oneMonthAgo);
        System.out.println("oneYearAge: "+oneYearAgo);
        
    }
}

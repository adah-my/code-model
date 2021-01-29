package xiaoce.middle.basal.timeapi;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class NewDateApiTest5
{

    /**
     * LocalDateTime 与 Date 互转
     * 媒介是 Instant（格林尼治时间）
     * @param args
     */
    public static void main(String[] args)
    {
        // 先把LocalDateTime变为ZoneDateTime，然后调用 toInstant()
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        // Instant是代表本初子午线的时间，所以比我们东八区要晚8小时
        Instant instant = zonedDateTime.toInstant();
        System.out.println("zoneDateTime: "+zonedDateTime);
        System.out.println("instant: "+instant+"\n");

        // 转为Date
        Date date = Date.from(instant);
        System.out.println("date: "+date+"\n");

        // Date 转 LocalDateTime
        Date date1 = new Date();
        // Date 也有 toInstant()
        Instant instant1 = date1.toInstant();
        System.out.println("data1: "+date1);
        System.out.println("instant: "+instant1+"\n");

        // 不带时区：LocalDateTime.of(), 带时区：LocalDateTime.ofInstant()
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
        System.out.println(localDateTime+"\n");

        // LocalDateTime 与 秒 互转
        long result = now.toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println(result);
        LocalDateTime nowLocalDate = LocalDateTime.ofEpochSecond(result, 0, ZoneOffset.ofHours(8));
        System.out.println(nowLocalDate+"\n");

        // 格式化
        System.out.println("格式化前："+now);
        String format = now.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("默认格式："+format);
        String basicFormat = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("其他格式："+basicFormat);

    }
}

package xiaoce.middle.basal.timeapi;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class NewDateApiTest4
{

    /**
     * 时区 Zone
     * @param args
     */
    public static void main(String[] args)
    {
        // 上面介绍的LocalDateTime等都是不包含时区信息的，但我们可以将它们转为包含时区信息的对象

        // 当地时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("localDateTime: "+now);
        // 时区（id的形式），默认的是本国时区
        ZoneId zoneId = ZoneId.systemDefault();
        // 为localDateTime补充时区信息
        ZonedDateTime zonedDateTime = now.atZone(zoneId);
        System.out.println("zoneDateTime: "+zonedDateTime);

        // LocalDateTime转为ZoneDateTime时间不会变，仅仅是丰富了时区信息而已
        ZoneId ofZoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime asiaZonedDateTime = now.atZone(ofZoneId);
        System.out.println("tokyoTime: "+asiaZonedDateTime);

        // 那么，怎么转换时区时间呢？
        // 当前上海时间对应东京时间是几点呢？
        ZonedDateTime tokyoDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("tokyoDateTime: "+tokyoDateTime);

        // 所以，现在明白为什么旧的时间API叫Date，而新的时间API叫LocalXxx了吗？
        // 因为它不带时区，只是一个普普通通的时间概念

        // 获取所有的时区Id (并没有什么用)
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(zoneIds);

        // ZoneDateTime 转回 LocalDateTime
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        System.out.println("localDateTime: "+localDateTime);

    }
}

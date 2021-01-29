package xiaoce.middle.basal.timeapi;

import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class NewDateApiTest3
{

    public static void main(String[] args)
    {

        /**
         * 如何修改时间 with
         * 注意：
         * plus时用的是ChronoUnit，表示增幅单位，而这里ChronoFiled则是指定修改的字段
         */
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now: "+now);
        // 将day修改为6号
        LocalDateTime modifiedDateTime = now.with(ChronoField.DAY_OF_MONTH, 6);
        System.out.println("modifiedDateTime: "+modifiedDateTime);

        /**
         * 如何比较时间 isAfter/ifBefore/isEqual
         */
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minus(1, ChronoUnit.DAYS);
        LocalDateTime after = today.plusSeconds(1);

        boolean result = after.isAfter(today);
        boolean beforeResult = yesterday.isBefore(today);
        boolean equal = today.isEqual(after);
        System.out.println(result);
        System.out.println(beforeResult);
        System.out.println(equal);

    }
}

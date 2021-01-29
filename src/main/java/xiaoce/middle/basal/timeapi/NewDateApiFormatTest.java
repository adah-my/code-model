package xiaoce.middle.basal.timeapi;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class NewDateApiFormatTest
{

    /**
     * 格式化
     * @param args
     */
    public static void main(String[] args)
    {
        // 格式化
        LocalDateTime now = LocalDateTime.now();
        System.out.println("格式化前："+now);
        String format = now.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("默认格式："+format);
        String basicFormat = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("其他格式："+basicFormat+"\n");

        // 自定义格式
        System.out.println("格式化前："+now);
        String format1 = now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"));
        System.out.println("自定义格式化后："+format1);

        // 反格式化
        LocalDateTime parse = LocalDateTime.parse(format1, DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"));
        System.out.println("反格式化："+parse);
    }    
}

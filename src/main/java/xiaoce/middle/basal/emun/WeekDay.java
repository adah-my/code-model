package xiaoce.middle.basal.emun;

import lombok.Getter;

@Getter
public class WeekDay
{
    public static final WeekDay MONDAY;
    public static final WeekDay TUESDAY;
    public static final WeekDay WEDNESDAY;
    public static final WeekDay THURSDAY;
    public static final WeekDay FIRDAY;
    public static final WeekDay SATURDAY;
    public static final WeekDay SUNDAY;

    private static final WeekDay[] VALUES;

    static {
        /**
         * final字段赋值有三种形式：
         * 显式赋值，private final Integer code = 1
         * 静态代码块/代码块赋值
         * 构造器赋值
         */
        MONDAY = new WeekDay(1, "星期一");
        TUESDAY = new WeekDay(2, "星期一");
        WEDNESDAY = new WeekDay(3, "星期一");
        THURSDAY = new WeekDay(4, "星期一");
        FIRDAY = new WeekDay(5, "星期一");
        SATURDAY = new WeekDay(6, "星期一");
        SUNDAY = new WeekDay(7, "星期一");
        // 在加载类时就收集所有的WeekDay对象
        VALUES = new WeekDay[]{
                MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FIRDAY, SATURDAY, SUNDAY
        };
    }

    /**
     * 校验前端传入的code是否合法
     * @param code
     * @return
     */
    public static WeekDay of(Integer code){
        for (WeekDay weekDay : VALUES){
            if (weekDay.code.equals(code)){
                return weekDay;
            }
        }
        throw new IllegalArgumentException("Invalid Enum code:"+code);
    }


    private WeekDay(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private final Integer code;
    private final String desc;
}

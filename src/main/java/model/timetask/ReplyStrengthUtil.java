package model.timetask;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author muyi
 * @description:
 * @date 2020-11-06 16:21:39
 */
public class ReplyStrengthUtil {



    public ReplyStrengthUtil(){

    }


    public static void main(String[] args) {

    }


    /**
     * 每5分钟执行一次
     */
    public void executeFiveMinutes() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        long fiveMinutes = 5 * 60 * 1000;
        long initDelay = getTimeMillis("14:00:00") - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : fiveMinutes + initDelay;

        executor.scheduleAtFixedRate(() -> {

            // 每5分钟执行一次 内存 redis 数据库中所有的体力
            System.out.println("自动执行回体~");

        }, initDelay, fiveMinutes, TimeUnit.MILLISECONDS);
    }




    /**
     * 获取指定时间对应的毫秒数
     *
     * @param time
     *            "HH:mm:ss"
     * @return
     */
    private long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

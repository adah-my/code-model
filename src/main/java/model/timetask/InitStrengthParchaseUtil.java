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
 * @date 2020-11-06 17:40:36
 */
public class InitStrengthParchaseUtil {




    public InitStrengthParchaseUtil(){

    }


    /**
     * 每天凌晨0点执行一次 每天定时安排任务进行执行
     */
    public void executeAtMidnightPerDay() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        long oneDay = 24 * 60 * 60 * 1000;
        // 执行任务的时间
        long initDelay = getTimeMillis("16:00:00") - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;

        executor.scheduleAtFixedRate(() -> {

            // 0点更新 体力购买次数
            System.out.println("0点刷新用户购买的体力次数");


        }, initDelay, oneDay, TimeUnit.MILLISECONDS);
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

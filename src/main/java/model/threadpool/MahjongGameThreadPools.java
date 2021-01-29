package model.threadpool;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * @author muyi
 * @description:
 * @date 2020-11-19 19:48:26
 */
public class MahjongGameThreadPools {

    /**
     * 真人玩家辅助线程池
     */
    public static ThreadPoolExecutor helpThreadPool;
    /**
     * ai摸牌出牌执行线程池
     */
    public static ScheduledThreadPoolExecutor robotThreadPool;
    /**
     * ai检测其他牌吃碰杠胡线程池
     */
    public static ThreadPoolExecutor actionThreadPool;

    static {
        helpThreadPool = new ThreadPoolExecutor(
                5,
                100,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(50),
                new DefaultThreadFactory("player-help")
        );
        robotThreadPool = new ScheduledThreadPoolExecutor(
                10,
                new DefaultThreadFactory("robot-action")
        );

        actionThreadPool = new ThreadPoolExecutor(
                5,
                20,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(50),
                new DefaultThreadFactory("special-action")
        );
    }

    /**
     * 提交任务
     *
     * @param command
     */
    public static void executeByHelpThreadPool(Runnable command) {
        helpThreadPool.execute(command);
    }

    /**
     * 提交延时任务
     *
     * @param command
     * @param delay
     * @param unit
     * @return
     */
    public static ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return MahjongGameThreadPools.robotThreadPool.schedule(command, delay, unit);
    }

    /**
     * 提交任务
     *
     * @param command
     */
    public static void executeByActionThreadPool(Runnable command) {
        actionThreadPool.execute(command);
    }

    public static void main(String[] args) {
        System.out.println(0>>1);
    }

}

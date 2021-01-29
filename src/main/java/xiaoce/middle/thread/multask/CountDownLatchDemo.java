package xiaoce.middle.thread.multask;

import com.jfinal.template.stat.ast.ForIteratorStatus;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        // 1.先看看简单demo，了解下CountDownLatch
//        mainThreadAndAsyncThread();
        // 2.尝试使用CountDownLatch解决任务并行问题（不处理结果）
        multiThreadTask();
    }

    /**
     * CountDownLatch简单demo
     */
    private static void mainThreadAndAsyncThread() throws InterruptedException
    {
        // 准备一个countDownLatch，设置10，相当于给门加了10把锁
        CountDownLatch countDownLatch = new CountDownLatch(10);

        long start = System.currentTimeMillis();

        // 副线程取处理任务，每个任务耗时1秒，每处理完1个任务就解开一把锁
        new Thread(() -> {
            for (int i = 0; i < 10; i++)
            {
                try
                {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                countDownAndPrint(countDownLatch, 1);
            }
        }).start();

        // 一夫当关，万夫莫开。只要门上10把锁没有全部解开，任务线程都别想往下走
        countDownLatch.await();

        System.out.println("all task is completed! cost:" + (System.currentTimeMillis()-start) + "ms");
    }

    /**
     * CountDownLatch应用：演示10个任务并执行，全部完成后返回结果
     */
    private static void multiThreadTask() throws InterruptedException
    {
        // 准备一个countDownLatch，设置10，相当于给men加了10把锁
        CountDownLatch countDownLatch = new CountDownLatch(10);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10; i++)
        {
            new Thread(() -> {
                // 线程进来执行任务，随机0~4秒
                int seconds = ThreadLocalRandom.current().nextInt(5);
                try
                {
                    TimeUnit.SECONDS.sleep(seconds);
                    countDownAndPrint(countDownLatch, seconds);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }).start();
        }

        // 一夫当关，万夫莫开。只要门上10把锁没有全部解开，任何线程都别想往下走
        countDownLatch.await();

        System.out.println("all task is completed! cost: " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * countDown()并且打印，其实是不需要加synchronized的，这里只是为了多线程环境下正确打印
     * @param countDownLatch
     * @param seconds
     */
    private static synchronized void countDownAndPrint(CountDownLatch countDownLatch, int seconds)
    {
        countDownLatch.countDown();
        System.out.println("task completed, cost: " + seconds + "s left: " + countDownLatch.getCount());
    }


}

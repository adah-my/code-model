package xiaoce.middle.thread.futuretask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ParkTest
{
    @Test
    public void testPark() throws InterruptedException
    {

        // 存储线程
        ArrayList<Thread> threadList = new ArrayList<>();

        // 创建5个线程
        for (int i = 0; i < 5; i++)
        {
            Thread thread = new Thread(() -> {
                System.out.println("我是" + Thread.currentThread().getName() + "，我开始工作了~");
                LockSupport.park(this);
                System.out.println("我是" + Thread.currentThread().getName() + "，我又活过来了~");
            });
            thread.start();
            threadList.add(thread);
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println("============= 所有线程都阻塞了，3秒后全部恢复了 ============");

        // unPark() 所有线程
        for (Thread thread : threadList)
        {
            LockSupport.unpark(thread);
        }

        // 等所有线程执行完毕
        TimeUnit.SECONDS.sleep(3);
    }
}

package xiaoce.middle.thread.futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncAndWaitTest
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
//        // 方式一：重写Thread#run()
//        Thread thread = new Thread()
//        {
//            @Override
//            public void run()
//            {
//                System.out.println(Thread.currentThread().getName() + "--------->正在执行");
//            }
//        };
//        thread.start();
//
//
//        // 方式二：构造方法传入Runnable实例
//        new Thread( () -> {
//            System.out.println(Thread.currentThread().getName()+ "------------>正在执行");
//        } ).start();
//
//        // 方式3：线程池 + Callable
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<String> submit = executorService.submit(() -> {
//            System.out.println(Thread.currentThread().getName() + "------------>正在执行");
//            Thread.sleep(3 * 1000L);
//            return "success";
//
//        });
//        String result = submit.get();
//        System.out.println("result ----------->"+result);
//        // 关闭线程池
//        executorService.shutdown();

        System.out.println(Thread.currentThread().getName()+"主线程执行开始");
        Worker worker = new Worker();
        worker.begin();
        System.out.println(Thread.currentThread().getName()+"主线程执行结束");
        Thread.sleep(4 * 1000L);
    }


    static class Worker implements Runnable{

        /**
         * 自定义的begin方法，会开启一个新线程，把当前对象作为任务对象
         */
        public void begin(){
            new Thread(this).start();
        }

        @Override
        public void run()
        {
            System.out.println(Thread.currentThread().getName()+"执行Worker#run()开始");
            try
            {
                Thread.sleep(3 * 1000L );
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行Worker#run()结束");
        }
    }
}

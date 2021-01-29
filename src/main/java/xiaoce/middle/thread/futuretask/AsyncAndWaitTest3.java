package xiaoce.middle.thread.futuretask;

import java.util.concurrent.*;

public class AsyncAndWaitTest3
{
    /**
     * 何时调用FutureTask.get()
     * 之前说了，用户调用get()必然是想要得到最终结果，所以为了保证一定能得到结果，JDK把FutureTask#get()设计成阻塞的
     * 如果运气好，get()的时候可能任务刚好结束于是立即返回。如果运气不好，可能要等很久。所以，什么时候调用FutureTask#get()呢？
     * ！！！强烈建立不要立即调用get()，否则程序完全没有发挥异步优势，由异步阻塞变成同步阻塞
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        // 单线程的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 为了看清楚，把Callable提出来赋值
        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName()+"-------->正在执行");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("3秒");
            return "success";
        };

        // 提交Callable任务
        Future<String> result = executorService.submit(callable);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1秒");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2秒");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("result = "+result.get());
    }



}

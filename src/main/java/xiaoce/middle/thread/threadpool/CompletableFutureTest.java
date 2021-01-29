package xiaoce.middle.thread.threadpool;

import lombok.SneakyThrows;
import org.junit.Test;

import java.sql.Time;
import java.util.concurrent.*;

public class CompletableFutureTest
{

    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    /**
     * 轮询异步结果并获取
     * 实际编程中如果期望得到异步结果，一般有两种方式：
     *      FutureTask#get()阻塞等待
     *      判断FutureTask#isDone()，如果为ture则返回
     *
     * FutureTask的不足
     * FutureTask其实各方面都比较完美，初见时甚至让人惊艳，因为它允许我们获取异步执行的结果！但FutureTask#get()本身时阻塞的，假设当前有三个下载任务在执行：
     *      task1（预计耗时5秒）
     *      task2（预计耗时1秒）
     *      task3（预计耗时1秒）
     * 如果阻塞获取时不凑巧把task1.get()排在最前面，那么会造成一定的资源浪费，因为task2和task3早就已经准备好了，可以先拿出来处理，以获取最佳的用户体验
     * 
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        // 任务一
        Future<String> runnableFuture = executor.submit(new Runnable()
        {
            @SneakyThrows
            @Override
            public void run()
            {
                System.out.println("Runnable异步线程开始。。。");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Runnable异步线程结束。。。");
            }
        }, "fakeRunnableResult");

        // 任务二
        Future<String> callableFuture = executor.submit(new Callable<String>()
        {
            @Override
            public String call() throws Exception
            {
                System.out.println("Callable异步线程开始。。。");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Callable异步线程结束。。。");
                return "callableResult";
            }
        });

        boolean runnableDone = false;
        boolean callableDone = false;

        // 不断轮询，知道所有任务结束
        while (true){
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("轮询异步结果。。。");
            if (runnableFuture.isDone()){
                System.out.println("Runnable执行结果："+runnableFuture.get());
                runnableDone = true;
            }

            if (callableFuture.isDone()){
                System.out.println("Callable执行结果："+callableFuture.get());
                callableDone = true;
            }

            if (runnableDone && callableDone){
                break;
            }
        }

        System.out.println("任务全部结束");
    }
}

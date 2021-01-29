package xiaoce.middle.thread.threadpool;

import lombok.SneakyThrows;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureTest3
{

    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        // 任务一：把第一个任务推进去，顺便开启异步线程
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(new Supplier<String>()
        {
            @SneakyThrows
            @Override
            public String get()
            {
                System.out.println("--------->异步线程开始。。。");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("--------->completableFuturen1任务结束。。。");
                System.out.println("---------->执行completableFuturen1的线程为：" + Thread.currentThread().getName());
                return "supplierResult";
            }
        });
        System.out.println("completableFuture1:"+completableFuture1);

        // 任务二：把第二个任务推进去，等待异步回调
        CompletableFuture<String> completableFuture2 = completableFuture1.thenApply(new Function<String, String>()
        {
            @SneakyThrows
            @Override
            public String apply(String s)
            {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("------------>completableFuture2任务结束 result=" + s);
                System.out.println("---------->执行completableFuturen1的线程为：" + Thread.currentThread().getName());
                return s;
            }
        });
        System.out.println("completableFuture2:"+completableFuture2);

        CompletableFuture<String> completableFuture3 = completableFuture2.thenApply(new Function<String, String>()
        {
            @SneakyThrows
            @Override
            public String apply(String s)
            {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("----------->completableFuture3任务结束 result=" + s);
                System.out.println("----------->执行completableFuture3的线程为：" + Thread.currentThread().getName());
                return s;
            }
        });
        System.out.println("completableFuture3:"+completableFuture3);
        TimeUnit.SECONDS.sleep(60);
    }
}

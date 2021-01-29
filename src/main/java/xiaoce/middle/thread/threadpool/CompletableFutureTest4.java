package xiaoce.middle.thread.threadpool;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureTest4
{


    /**
     * 1、主线程在执行任务一和任务二、任务三分别做了什么操作？
     * 主线程执行任务一时，通过调用CompletableFuture#supplyAsync(Supplier#get),把需要异步执行的”任务传递进去“，
     * 内部会将Supplier#get()包装成AsyncApply对象，它继承自ForkJoinTask，可以丢给ForkJoinPool#execute()执行，等到线程池分出异步线程后，会通过一连串的调用，
     * 最终执行AsyncApply#run()，进而执行Supplier#get().异步线程对结果的获取还是阻塞的
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        // 任务一
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(new Supplier<String>()
        {
            @Override
            public String get()
            {
                return "supplierResult";
            }
        });
        System.out.println("completableFuture1:"+completableFuture1);

        // 任务二
        CompletableFuture<String> completableFuture2 = completableFuture1.thenApply(new Function<String, String>()
        {
            @Override
            public String apply(String s)
            {
                return s;
            }
        });
        System.out.println("completableFuture2:"+completableFuture2);

        CompletableFuture<String> completableFuture3 = completableFuture2.thenApply(new Function<String, String>()
        {
            @Override
            public String apply(String s)
            {
                return s;
            }
        });
        System.out.println("completableFuture3:"+completableFuture3);

        System.out.println("主线程结束");
        TimeUnit.SECONDS.sleep(40);
    }
}

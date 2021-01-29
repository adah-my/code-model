package xiaoce.middle.thread.threadpool;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CompletableFutureTest2
{

    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    /**
     *
     * ConpletableFuture：基于异步回调的Future
     * CompletableFuture VS FutureTask
     *
     * CompletableFuture相当于一个自带线程池的Future，而CompletableFuture#supplyAsync(supplier)倒像是ExecutorService#submit(Runnable/Callable)
     * 内部也会包装任务，最终丢给Executor#execute(Task)。只不过ExecutorService是把Runnable#run()/Callable#call()包装成FutureTask。
     * erCompletableFuture则把乱七八糟的supplier#get()等函数式接口的方法包装成ForkJoinTask
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        // 提交一个任务，返回CompletableFuture
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>()
        {
            @SneakyThrows
            @Override
            public String get()
            {
                System.out.println("------------>异步线程开始。。。");
                System.out.println("------------>异步线程为：" + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(10);
                System.out.println("------------>异步线程结束。。。");
                return "supplierResult";
            }
        });


        // 异步回调
        completableFuture.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String result, Throwable throwable)
            {
                System.out.println("------------->异步任务结束回调。。。"+result);
                System.out.println("------------->回调线程："+Thread.currentThread().getName());
            }
        });
        System.out.println("main结束");
        TimeUnit.SECONDS.sleep(15);

        // 阻塞获得结果
//        System.out.println("异步的结果是："+completableFuture.get());
    }
}

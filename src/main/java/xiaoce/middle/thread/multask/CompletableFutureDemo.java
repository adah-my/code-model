package xiaoce.middle.thread.multask;


import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureDemo
{
    private static AtomicInteger count = new AtomicInteger(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        // 准备10个线程
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 收集结果
        ArrayList<CompletableFuture<Integer>> resultList = new ArrayList<>();

        long start = System.currentTimeMillis();

        // 任务并行
        for (int i = 0; i < 10; i++)
        {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                // 模拟任务耗时 0~4秒
                try
                {
                    int seconds = ThreadLocalRandom.current().nextInt(5);
                    TimeUnit.SECONDS.sleep(seconds);
                    System.out.println("task is completed! cost:" + seconds + "s left: " + count.decrementAndGet());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return 1;
            }, executorService);
            resultList.add(completableFuture);
        }

        // 处理结果
        int result = 0;
        for (CompletableFuture<Integer> completableFuture : resultList)
        {
            result += completableFuture.get();
        }

        // 最终全部任务完成，总耗时取决于最耗时的任务时长
        System.out.println("all task completed! result=" + result + " cost: " + (System.currentTimeMillis()-start) + "ms");
    }

}

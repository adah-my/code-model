package xiaoce.middle.thread.multask;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureTaskDemo
{
    private static AtomicInteger count = new AtomicInteger(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        // 准备10个线程
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 收集每个任务的结果
        ArrayList<Future<Integer>> resultList = new ArrayList<>();

        long start = System.currentTimeMillis();

        // 并行处理10个任务
        for (int i = 0; i < 10; i++)
        {
            Callable<Integer> task = () -> {
                // 模拟任务耗时0~4秒
                int seconds = ThreadLocalRandom.current().nextInt(5);
                TimeUnit.SECONDS.sleep(seconds);
                System.out.println("task is completed! cost：" + seconds + "s left: "+count.decrementAndGet());
                // 模拟返回结果
                return 1;
            };
            // 提交任务
            Future<Integer> partResult = executorService.submit(task);
            // 收集结果
            resultList.add(partResult);
        }

        int result = 0;

        // 阻塞获取并累加结果
        for (Future<Integer> future : resultList)
        {
            result += future.get();
        }

        // 最终全部任务完成，总耗时取决于最耗时的任务时长
        System.out.println("all task is completed! result=" + result + " cost: " + (System.currentTimeMillis()-start) + "ms");

    }
}

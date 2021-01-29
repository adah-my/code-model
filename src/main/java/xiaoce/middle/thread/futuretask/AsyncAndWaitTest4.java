package xiaoce.middle.thread.futuretask;

import java.sql.Time;
import java.util.concurrent.*;

public class AsyncAndWaitTest4
{
    /**
     * isDone() + get()
     * 但是实际开发时，异步线程具体会耗时多久有时很难预估，受网络、数据库等各方面影响。所以很难做到在合适的地方get()然后一击即中
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        // 单线程的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 提交Callable任务
        Future<String> result = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName()+"-------->正在执行");
            TimeUnit.SECONDS.sleep(3);
            return "success";
        });

        
        TimeUnit.SECONDS.sleep(2);

        if (result.isDone()){
            System.out.println("异步线程结束了，获取结果："+result.get());
        }else{
            System.out.println("异步线程尚未结束，稍后再试");
        }

        TimeUnit.SECONDS.sleep(2);

        if (result.isDone()){
            System.out.println("异步线程结束了，获取结果："+result.get());
        }else{
            System.out.println("异步线程尚未结束，稍后再试");
        }

    }



}

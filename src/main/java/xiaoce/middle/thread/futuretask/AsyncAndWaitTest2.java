package xiaoce.middle.thread.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class AsyncAndWaitTest2
{
    /**
     * FutureTask = 任务 + 结果
     * 总结一下，FutureTask有以下两个特征：
     * 能包装Runnable和Callable（构造器传入），但本身却又实现了Runnable接口，即本质是Runnable
     * 既然是Runnable，所以FutureTask能作为任务被Thread执行，但诡异的是FutureTask#get()可以获取结果
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        // FutureTask实现了Runnable，可以看做是一个任务
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>()
        {
            @Override
            public String call() throws Exception
            {
                System.out.println(Thread.currentThread().getName()+"----------->正在执行");
                TimeUnit.SECONDS.sleep(3);
                return "success";
            }
        });
        System.out.println(Thread.currentThread().getName() + "----------->启动任务");

        // 传入futureTask，启动线程执行任务
        new Thread(futureTask).start();

        // 但同时又实现了FutureTask.get()
        String result = futureTask.get();
        System.out.println("任务执行结束，result--------->"+result);

        // FutureTask包装Runnable（通过构造器）
        FutureTask<String> runnable = new FutureTask<>(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(Thread.currentThread().getName()+"------------->正在执行");
            }
        }, "success");

        // FutureTask包装Callable（通过构造器）
        FutureTask<String> callable = new FutureTask<>(new Callable<String>()
        {
            @Override
            public String call() throws Exception
            {
                System.out.println(Thread.currentThread().getName() + "-------------->正在执行");
                return "success";
            }
        });

        // Runnable --> Executors.callable() --> RunnableAdapter implements Callable --> FutureTask.callable
        // Callable --> FutureTask.callable
        
    }



}

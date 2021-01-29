package xiaoce.middle.thread.threadpool;

public class ExecutorExtends
{
    /**
     * 线程池继承体系
     * 第一阶段：线程池顶级接口为Executor，它只规定了一个execute()方法，过于单薄
     *
     * 第二阶段：ExecutorService接口继承Executor并新增展示线程池状态的方法，submit()及Future返回值
     *      ExecutorService的贡献有两点：
     *      突破性地引入”线程池状态“的概念，要求子类必须实现诸如shutdown()、shutDownNow()、isTerminated()、isShutDown()等方法以便反馈线程池的状态：已关闭？任务已结束？
     *      另外还引入了submit()方法。它的灵感来自于Executor的execute()，但execute()是没有返回值的，而submit()会返回一个Future对象
     *
     * 第三阶段：如果说ExecutorService已经基本规定了一个线程池需要提供哪些功能，那么ScheduledExecutorService则在此基础上提出了定时任务线程池的概念，
     * 也就是JDK认为定时任务线程池是个更细化的领域，单独抽取一个接口描述
     *      ScheduledExecutorService要求子类实现定时方法，能够周期性地执行任务。与submit()类似，当任务提交给这些定时方法后，会返回ScheduledFuture，本质也是用来掌控任务进度的
     *
     * 第四阶段：AbstractExecetorService：继往开来
     *      AbstractExecutorService做了两件事：
     *      新增newTaskFor()方法，将Runnable和Callable两种任务统一为FutureTask类型
     *      对ExecutorService接口的submit()等方法做了初步实现
     *
     * ！！！！！
     * 很多人都是机械性的记忆：submit()可以接受Runnable/Callable并有返回值，execute()只能接收Runnable并且没有返回值
     * 但是她们并没有意识到submit() --> Runnablej/Callable统一为FurureTask --> execute()，即submit()底层调用的是execute()
     * 且不论给线程池丢Runnable还是Callable，最终稿都会变成FutureTask
     * ！！！！！
     * 也就是说
     * Runnable --> Executors.callable() --> RunnableAdapter implements Callable --> FutureTask.callable
     * Callable --> FutureTask.callable
     *
     * 第五阶段：ForkJoinPool & ThreadPoolExecutor & ScheduledExecutorService：三国鼎立
     *      JDK线程池设计发展到这一步，终于出现了三个划时代的实现类
     *
     * 六：ThreadPoolExecutor实现类（重点）
     * 这是第一个拿来就能用的实现类！！！
     * ThreadPoolExecutor的贡献有以下几点：
     *      首次引入 corePoolSize、maximumPoolSize、keepAliveTime、ThreadFactory等参数配置，真正实现了线程的”池化“。
     *      线程池里的线程数会在这几个阈值的影响下动态调整，大大提高了执行效率和复用率
     *      将任务包装成Worker，加入阻塞队列，缓冲执行
     *      内置4种拒绝策略，释放执行压力
     *
     * 整个submit调用链时：
     * submit(Runnable/Callable) --> 包装成FutureTask --> execute(FutureTask) --> addWorker(FutureTask)
     * --> 异步线程执行worker.run() --> runWorker(Worker) --> woker.futureTask.run()
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * 
     */

}

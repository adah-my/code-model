package xiaoce.middle.redislock;

public class LockExplain
{
    /**
     *
     * 浅谈Redis分布式锁
     *
     * 一、JVM锁
     * 所谓JVM锁，其实指的是诸如synchronized关键字或者ReentrantLock实现的锁。之所以统称为JVM锁，是因为我们的项目其实都是跑在JVM上的。
     * 理论上每一个项目启动后，就对应一一片JVM内存，后续运行期时数据生离死别都是在这一片土地上。
     *
     * 二、什么是锁？怎么锁？
     * 当同时满足以下三个条件时，才可能引发线程安全问题：
     *      多线程环境
     *      有共享数据
     *      有多条语句操作共享数据/单条语句本身非原子操作（比如i++虽然是单条语句，但并非原子操作）
     *
     * JVM锁其实就是线程和线程彼此的”中间人“，多个线程在操作加锁数据前都必须征求”中间人“的同意：
     * 在JDK中，锁的实现机制最常见的就是两种，分别是两个派系：
     *      synchronized关键字
     *      CAS+AQS
     *
     * 三、Java对象内存结构
     * Java对象内存结构大致分为几块：
     *      Mark Word （锁相关） Mark Word包含的信息还是蛮多的，但这里我们只需要简单地把它理解为记录锁信息的标志即可。
     *      元数据指针 （class pointer，指向当前实例所属的类）
     *      实例数据 （instance data，我们平常看到的仅仅是这一块）
     *      对齐 （padding，和内存对齐有关）
     *
     * 所谓的this锁就是当前对象，而Class锁就是当前对象所属类的Class对象，本质也是Java对象。
     * synchronized修饰的普通方法底层使用当前对象作为锁，synchronized修饰的静态方法底层使用Class对象作为锁。
     * 但如果要保证多个线程互斥，最基本的条件是它们使用同一把锁，对同一份数据加两把锁是没有意义的。
     *
     * 四、synchronized与锁升级
     * 在JDK早期版本中，synchronized关键字的实现是直接基于重量级锁的。只要我们在代码中使用的synchronized，JVM就会向操作系统申请锁资源（不论当前是否真的是多线程环境），
     * 而向操作系统申请锁是比较耗费资源的，其中涉及到用户态和内核态的切换等，总之就是比较费事，且性能不高。
     * JDK为了解决JVM锁性能地下的问题，引入了ReentrantLock，它基于CAS+AQS，类似自旋锁。自旋的意思就是，在发生锁竞争的时候，为竞争到锁的线程会在门外采取自旋的方式等待锁的释放，谁抢到谁执行。
     *
     * 自旋锁的好处是，不需要兴师动众地切换到内核态申请操作系统的重量级锁，在JVM层面即可实现自旋等待。
     * 虽然避免了状态切换等复杂操作，却要耗费部分CPU资源，尤其是高并发上锁时间长的情况下，会极大增加CPU的负担。
     *
     * 所以大概在JDK1.6或者更早版本，官方对synchronized做了优化，提出了”锁升级“的概念，把synchronized的锁划分为多个状态：
     *      无锁
     *      偏向锁
     *      轻量级锁（自旋锁）
     *      重量级锁
     *
     * 无锁就是一个Java对象刚new出来的状态。当这个对象第一次被一个线程访问时，该线程会把自己的线程id”贴到“它的头上（Mark Word中部分位数被修改），此时是不存在锁竞争的，所以并不会有什么阻塞或等待。
     * 而一旦发生锁竞争是，synchronized便会在一定条件下升级为轻量级锁，可以理解为一种自旋锁，具体自选多少次以及何时放弃自旋，JDK也有一套相关的控制机制。
     * 同样是自旋，所以synchronized也会遇到ReentrantLock的问题：如果上锁时间长且自旋线程多，又该如何？
     * 此时就会再次升级，变成传统意义上的重量级锁，本质上操作系统会维护一个队列，用空间换时间，避免多个线程同时自旋等待耗费CPU性能，等到上一个线程结束时唤醒等待的线程参与新一轮锁竞争即可。
     *
     * synchronized是可重入锁，可以粗浅地理解为同一个线程在已经持有该锁的情况下，可以再次获取锁，并且会在某个状态量上做+1操作（ReentrantLock也支持重入）
     *
     * 
     * 五、Redis分布式锁
     *
     * 谈到Redis分布式锁，总是会有这样或那样的疑问：
     *      什么是分布式
     *      什么是分布式锁
     *      为什么需要分布式锁
     *      Redis如何实现分布式锁
     *
     * 分布式有个很显著的特点是，Service A和Service B极有可能并不是部署在同一个服务器上，所以它们也不共享同一片JVB内存。
     * 而上面介绍了，要想实现线程互斥，必须保证所有访问的线程使用的是同一把锁（JVM锁此时就无法保证互斥）。
     * 对于分布式项目，有多少台服务器就有多少片JVM内存，即使各设置一把”独一无二“的锁，从整体来看项目的锁就不是唯一的。
     *
     * 此时，如何保证每一个JVM上的线程共用一把锁呢？ 答案是：把锁抽取出来，让线程们在同一片内存相遇。
     *
     * 但锁是不能凭空存在的，本质上还是要在内存中，此时可以使用Redis缓存作为锁的宿主环境，这就是Redis能钩爪分布式锁的原因。
     *
     *
     *
     * 思考：分布式系统如果要加锁是否一定要使用分布式锁呢？（可能未必）
     *
     * 如果你需要的是写锁，那么可能确实需要分布式锁保证单一线程处理数据，而如果是为了防止缓存击穿（热点数据定时失效），那么使用JVM本地锁也没有太大关系。
     * 比如某个服务器有10个节点，在使用JVM锁的情况下，即使某一时刻每个节点各自涌入1000个请求，虽然总共有1w个请求，但是最终打到数据库的也只有10个，
     * 数据库层面是完全可以抗住这点请求量的，又由于本身是查询，所以不会造成线程安全问题。
     *
     *
     *
     */

























































































}

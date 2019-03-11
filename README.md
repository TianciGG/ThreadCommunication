# ThreadCommunication多线程之间通讯
该项目包括：1.SynchronizedSharedThread、LockSharedResource分别为使用多线程的Synchronized同步锁、Lock并发包中的重入锁ReentrantLock实现对共享资源的读写分离操作，一个线程负责读资源的一部分，一个线程负责写资源的一部分，类似于生产者消费者模式，一个生产一个消费，类似MQ中间件。
2.使用退出标识+interrupt()方法优雅的停止线程。
3.守护线程的使用。
4.Join方法的使用与练习。


1:wait与notify方法一定需要放在sychronized中

根据JVM规范，若线程没有获得obj的监视器锁，即规范中n == 0的情况下，会抛出IllegalMonitorStateException。
为什么需要在执行wait和notify方法时先获得对象锁呢？从规范中可以看出，wait和notify操作需要对对象的等待集合进行更改，而这两个更改本身就是竞态条件，因此需要同步。
在JVM的wait方法的实现中，需要释放已经获得对象监视器锁，从而允许执行notify的代码段获得锁并执行。

2:各个方法
void notifyAll()

解除所有那些在该对象上调用wait方法的线程的阻塞状态。该方法只能在同步方法或同步块内部调用。如果当前线程不是锁的持有者，该方法抛出一个IllegalMonitorStateException异常。
void notify()

随机选择一个在该对象上调用wait方法的线程，解除其阻塞状态。该方法只能在同步方法或同步块内部调用。如果当前线程不是锁的持有者，该方法抛出一个IllegalMonitorStateException异常。
void wait()

你等等,我让你
调用者线程进入阻塞

导致线程进入等待状态，直到它被其他线程通过notify()或者notifyAll唤醒。该方法只能在同步方法中调用。如果当前线程不是锁的持有者，该方法抛出一个IllegalMonitorStateException异常。
void wait(long millis)和void wait(long millis,int nanos)

导致线程进入等待状态直到它被通知或者经过指定的时间。这些方法只能在同步方法中调用。如果当前线程不是锁的持有者，该方法抛出一个IllegalMonitorStateException异常。

Object.wait()和Object.notify()和Object.notifyall()必须写在synchronized方法内部或者synchronized块内部，这是因为：这几个方法要求当前正在运行object.wait()方法的线程拥有object的对象锁。即使你确实知道当前上下文线程确实拥有了对象锁，也不能将object.wait()这样的语句写在当前上下文中
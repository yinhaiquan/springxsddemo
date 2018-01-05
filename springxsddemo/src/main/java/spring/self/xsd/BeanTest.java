package spring.self.xsd;


import spring.self.xsd.bean.Facade;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/26 17:11 星期二
 */
public class BeanTest {
    private String name;
    private Facade facade;
    private volatile boolean tag;
    public volatile int j;

    public BeanTest() {
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Facade getFacade() {
        return facade;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    @Override
    public String toString() {
        return "BeanTest{" +
                "name='" + name + '\'' +
                ", facade=" + facade +
                '}';
    }

    public static class Thread2 implements Runnable{
        private BeanTest t1;

        public Thread2(BeanTest t1) {
            this.t1 = t1;
        }

        @Override
        public void run() {
            t1.setTag(true);
        }
    }

    public static class Thread3 implements Runnable{
        private BeanTest t1;
        private int i;

        public Thread3(BeanTest t1,int i) {
            this.t1 = t1;
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(i+":"+t1.isTag());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println("old:"+atomicInteger.getAndIncrement());
//        System.out.println(atomicInteger.incrementAndGet());
        System.out.println("new:"+atomicInteger.get());
        System.out.println("old:"+atomicInteger.getAndIncrement());
        System.out.println("new:"+atomicInteger.get());
        System.out.println("old:"+atomicInteger.getAndIncrement());
        System.out.println("new:"+atomicInteger.get());
        /*volatile 并发线程中可确保可见性，但无法保证原子性 自增(先取值，再加1，然后赋值内存，这三步可能分开执行，故打印t.j的值可能不是1000)
        * 保证原子操作，例如加LOCK锁、synchronized关键字或者使用AtomicInteger原子类型数据操作自增加一，即可保证原子性
        * */
//        final BeanTest t = new BeanTest();
//        for (int i=0;i<10; i++) {
//            new Thread(){
//                public void run(){
//                    for (int k = 0; k <1000; k++) {
//                        t.j++;
//                    }
//                }
//            }.start();
//        }
//
//        while(Thread.activeCount()>2)
//            Thread.yield();
//        System.out.println(t.j);

        /*volatile 可见性验证*/
//        BeanTest t1 = new BeanTest();
//        Thread2 t2 = new Thread2(t1);
//        int i = 0;
//        while(true){
//            i++;
//            if (i==10){
//                System.out.println("存在线程改变tag值为true");
//                new Thread(t2).start();
//            }
//            new Thread(new Thread3(t1,i)).start();
//        }


//        //计数信号器 用途：对部分重要代码可限制并发数量，只允许阀值内的线程进入
//        Semaphore sp = new Semaphore(3);
//        //强制公平机制
////        Semaphore sp = new Semaphore(3,true);
//        sp.acquire();
//        sp.release();
//        System.out.println(sp.getQueueLength());
//        /*是否启用强制公平机制*/
//        System.out.println(sp.isFair());
//        System.out.println(sp.hasQueuedThreads());
//        /*判断是否能获取许可*/
//        System.out.println(sp.tryAcquire());
//        System.out.println(sp.availablePermits());
//        System.out.println(sp.drainPermits());
    }
}

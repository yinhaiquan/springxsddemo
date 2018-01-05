package hystrix;

import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2018/1/3 14:56 星期三
 */
public class HystrixTest {

    /**
     * 同步阻塞调用方式
     */
    @Test
    public void testHelloHystrix(){
        HelloHystrixCommand helloHystrixCommand = new HelloHystrixCommand("world");
        String result = helloHystrixCommand.execute();
        System.out.println(result);
    }

    /**
     * 异步非阻塞调用方式
     */
    @Test
    public void testHelloHystrix2() throws ExecutionException, InterruptedException {
        Future<String> future = new HelloHystrixCommand("fuck").queue();
        String result = future.get();
        System.out.println(result);
    }

    /**
     * 热响应式调用，不管你是否已经订阅Hystrix会立即执行Command，
     * Hystrix会提供一种“ReplaySubject”方式进行过滤，所以不必担心
     * 已经执行但是还没被订阅的Command的结果会丢失。
     */
    @Test
    public void testHelloHystrix3() throws InterruptedException {
        Observable<String> observable = new HelloHystrixCommand("balabala").observe();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onnext业务处理完后操作!");
                System.out.println("方法执行完毕");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
        Thread.sleep(2000);
    }

    /**
     * 冷响应式调用，直到你订阅这个Command，Hystrix才会执行Command
     */
    @Test
    public void testHelloHystrix4() throws InterruptedException {
        Observable<String> observable = new HelloHystrixCommand("fadsf").toObservable();
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
        Thread.sleep(1000);
    }


    /**
     * 发布-订阅响应式的调用
     */
    @Test
    public void testHelloHystrixObservable() throws InterruptedException {
        HelloHystrixObservableCommand helloHystrixObservableCommand =
                new HelloHystrixObservableCommand("world2");
        Observable<String> observable = helloHystrixObservableCommand.observe();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onnext业务处理完后操作!");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
        Thread.sleep(2000);
    }
}

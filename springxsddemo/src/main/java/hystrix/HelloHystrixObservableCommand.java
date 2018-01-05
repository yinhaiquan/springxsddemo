package hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @title : 继承HystrixObservableCommand实现方式
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2018/1/3 15:08 星期三
 */
public class HelloHystrixObservableCommand extends HystrixObservableCommand<String>{
    private String somebody;

    protected HelloHystrixObservableCommand(String somebody) {
        super(HystrixCommandGroupKey.Factory.asKey("hello"));
        this.somebody = somebody;
    }

    @Override
    protected Observable<String> construct() {
        //生产环境中此处为第三方依赖的具体调用逻辑
        return Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()){
                    int i = 1/0; //测试异常回调方法
                    subscriber.onNext("Hello"+somebody);
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 重写异常回调方法
     * @return
     */
    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()){
                    subscriber.onNext("call failure...");
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}

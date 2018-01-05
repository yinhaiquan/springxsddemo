package hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @title : 继承HystrixCommand实现方式
 * @describle :
 * <p>
 *      我们可以通过实现父类构造函数来指定Command分组、命名、线程池，当然通过Setter对象设置是一种最通用的方式
 *      Setter setter = Setter
 *          .withGroupKey(HystrixCommandGroupKey.Factory.asKey("hello"))
 *          .andCommandKey(HystrixCommandKey.Factory.asKey("hello command"))
 *          .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hello thread pool"))
 *          .andCommandPropertiesDefaults(
 *          HystrixCommandProperties.Setter()
 *          .withExecutionTimeoutInMilliseconds(1000)
 *          )
 *          .andThreadPoolPropertiesDefaults(
 *          HystrixThreadPoolProperties.Setter()
 *          .withMaxQueueSize(10)
 *          );
 *      关于Command Group、Name和Thread-Pool三者的关系，在这里做一下说明，Group从业务逻辑上划分某些Command可以归为一组，
 *      每个独立的外部依赖放置于一个独立的Command中，拥有唯一的Command Name，每个独立的Command和Thread-Pool应该是一对一的关系，
 *      从而达到资源隔离的目的。
 * </p>
 * Create By yinhaiquan
 * @date 2018/1/3 14:41 星期三
 */
public class HelloHystrixCommand extends HystrixCommand<String> {
    private String somebody;

    public HelloHystrixCommand(String somebody) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hello")));
        this.somebody=somebody;
    }

    @Override
    protected String run() throws Exception {
        //生产环境中此处为第三方依赖的具体调用逻辑
        int i = 1/0; //测试异常回调方法
        return new StringBuilder().append("Hello").append(somebody).toString();
    }

    /**
     * 重新失败回调方法
     * @return
     */
    @Override
    protected String getFallback() {
        return "call failure...";
    }
}

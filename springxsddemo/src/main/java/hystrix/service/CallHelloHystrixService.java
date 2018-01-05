package hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @title : 测试hystrix注解使用
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2018/1/3 16:25 星期三
 */
@Service
public class CallHelloHystrixService {
    private Random random = new Random();

    @HystrixCommand(fallbackMethod = "callback")
    public String getRandomNumber(){
        int randmonInt = random.nextInt(10);
        System.out.println(randmonInt);
        if (randmonInt<3){
            throw new RuntimeException("模拟调用服务失败");
        }else {
            return "service is called sucessfully :"+randmonInt;
        }
    }

    private String callback(){
        return "rand number less than 8 。";
    }

}

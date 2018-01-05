package spring.test.fuck.test.control;

import aop.TestFrom;
import hystrix.service.CallHelloHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/27 14:31 星期三
 */
@Controller("test2")
@RequestMapping(value = "/test2")
public class TestController {

    @RequestMapping(value = "/test2",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String testForm2(@RequestParam("fk") int fuck,@RequestBody TestFrom testFrom){
        System.out.println(fuck);
        System.out.println(testFrom);
        return "hello2";
    }

}

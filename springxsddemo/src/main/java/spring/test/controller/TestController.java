package spring.test.controller;

import annotation.Validation;
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
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private CallHelloHystrixService callHelloHystrixService;

    @RequestMapping(value = "/test",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String testForm(@RequestBody TestFrom form){
        System.out.println(form);
        return "hello";
    }

    @RequestMapping(value = "/test2",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String testForm2(@RequestParam("fk") int fuck,@RequestBody TestFrom testFrom){
        System.out.println(fuck);
        System.out.println(testFrom);
        return "hello2";
    }

    @RequestMapping(value = "/hi",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String hi(){
        return callHelloHystrixService.getRandomNumber();
    }
}

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.self.xsd.BeanTest;
import spring.self.xsd.bean.Child;
import spring.self.xsd.bean.Demo2;
import spring.self.xsd.bean.Facade;
import spring.self.xsd.bean.Rmi;

import javax.annotation.Resource;


/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/11/14 14:58 星期二
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:*.xml"})
public class BaseTest {
    @Resource(name = "sdf")
    private Facade facade;

    @Resource(name = "iii")
    private Rmi rmi;

    @Resource(name ="fuck")
    private BeanTest beanTest;

    @Test
    public void testInit() throws InterruptedException {
        System.out.println("获取bean内容");
        System.out.println(beanTest);
        System.out.println(beanTest.getFacade());
//        System.out.println(facade);
//        System.out.println(rmi);
        System.out.println("测试xsd自定义标签结束");
    }

}

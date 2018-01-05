package spring.self.xsd.handler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/23 15:08 星期六
 */
public class FacadeNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("facade",new FacadeParser());
        registerBeanDefinitionParser("demo",new DemoParser());
        registerBeanDefinitionParser("demo2",new Demo2Parser());
    }
}

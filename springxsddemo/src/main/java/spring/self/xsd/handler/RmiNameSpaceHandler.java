package spring.self.xsd.handler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/23 15:08 星期六
 */
public class RmiNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("rmi",new RmiParser());
    }
}

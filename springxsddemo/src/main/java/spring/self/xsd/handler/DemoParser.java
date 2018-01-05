package spring.self.xsd.handler;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import spring.self.xsd.bean.Demo;

/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/26 13:49 星期二
 */
public class DemoParser extends AbstractSimpleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return Demo.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        builder.addPropertyValue("id",element.getAttribute("id"));
        builder.addPropertyValue("name",element.getAttribute("name"));
        builder.addPropertyValue("desc",element.getAttribute("desc"));
    }
}

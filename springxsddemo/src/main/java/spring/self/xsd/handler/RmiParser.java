package spring.self.xsd.handler;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import spring.self.xsd.bean.Facade;
import spring.self.xsd.bean.Rmi;

import javax.annotation.Resource;

/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/25 15:54 星期一
 */
public class RmiParser extends AbstractSimpleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Rmi.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        try {
            builder.addPropertyValue("id",element.getAttribute("id"));
            NodeList list = element.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                switch (list.item(i).getNodeName()){
                    case "rmi:itemId":
                        System.out.println(list.item(i).getTextContent());
                        builder.addPropertyValue("itemId", list.item(i).getTextContent());
                        break;
                    case "rmi:class":
                        builder.addPropertyValue("cls", Class.forName(list.item(i).getTextContent()));
                        break;
                    case "rmi:scope":
                        NodeList childs = list.item(i).getChildNodes();
                        System.out.println(childs.getLength());
                        builder.addPropertyValue("scope", "测试获取子节点数据");
                        break;
                    case "rmi:init":
                        System.out.println(list.item(i).getTextContent());
                        builder.addPropertyValue("init", list.item(i).getTextContent());
                        break;
                }
            }
        } catch (Exception e) {
            parserContext.getReaderContext().error("class " + FacadeParser.class.getName() + " can not be create", element, null, e);
        }
    }
}

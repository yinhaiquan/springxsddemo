package spring.self.xsd.handler;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import spring.self.xsd.bean.Child;
import spring.self.xsd.bean.Facade;

/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/23 15:09 星期六
 */
public class FacadeParser extends AbstractSimpleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return Facade.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        try {
            NodeList list = element.getChildNodes();
            /**
             * 解析元素子元素
             */
            for (int i = 0; i < list.getLength(); i++){
               Node node = list.item(i);
               switch (node.getNodeName()){
                   case "facade:birthday" :
                       System.out.println(node.getNodeName());
                       System.out.println(node.getTextContent());
                       builder.addPropertyValue("birthday",node.getTextContent());
                       break;
                   case "facade:child":
                       /**
                        * 解析子元素为对象的元素
                        */
                       System.out.println(node.getNodeName());
                       NodeList childs = node.getChildNodes();
                       Child child = new Child();
                       NamedNodeMap attributes = node.getAttributes();
                       System.out.println(attributes.getNamedItem("cls").getNodeName());
                       System.out.println(attributes.getNamedItem("cls").getTextContent());
                       System.out.println(attributes.getNamedItem("cls").getLocalName());
                       child.setCls(Class.forName(attributes.getNamedItem("cls").getTextContent()));
                       for (int j = 0; j < childs.getLength(); j++) {
                           switch (childs.item(j).getNodeName()){
                               case "facade:name" :
                                   child.setName(childs.item(j).getTextContent());
                                   break;
                               case "facade:age":
                                   child.setAge(Integer.valueOf(childs.item(j).getTextContent()));
                                   break;
                           }
                           System.out.println(childs.item(j).getNodeName());
                           System.out.println(childs.item(j).getTextContent());
                       }
                       builder.addPropertyValue("child",child);
                       break;
               }

            }
            builder.addPropertyValue("id",element.getAttribute("id"));
            builder.addPropertyValue("interCode",element.getAttribute("interCode"));
            builder.addPropertyValue("reqEvent",element.getAttribute("reqEvent"));
            builder.addPropertyValue("resEvent",element.getAttribute("resEvent"));
            builder.addPropertyValue("characterEncoding",element.getAttribute("characterEncoding"));
        } catch (Exception e) {
            parserContext.getReaderContext().error("class " + FacadeParser.class.getName() + " can not be create", element, null, e);
        }
    }
}

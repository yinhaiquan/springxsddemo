package spring.self.xsd.handler;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import spring.self.xsd.bean.Child;
import spring.self.xsd.bean.Demo2;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/26 13:49 星期二
 */
public class Demo2Parser extends AbstractSimpleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        try {
            return Class.forName(element.getAttribute("class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        System.out.println(element.getAttribute("id"));
        System.out.println(element.getAttribute("class"));
        /**获取spring注入的bean
         *
         *注意：再获取对象的时候确保获取的对象已被spring加载，否则将获取不到，原因：xml文件按照从上至下加载,有先后顺序区分
         *
         * parserContext.getRegistry() 获取spring bean注册器
         *
         * parserContext.getRegistry().getBeanDefinition("fk") 获取已注册的bean fk：为bean的ID
         *
         * parserContext.getRegistry().registerBeanDefinition  像spring 容器注册bean
         *
         */
        NodeList list = element.getChildNodes();
        for (int i =0;i<list.getLength();i++) {
            Node node = list.item(i);
            switch(node.getNodeName()){
                case "facade:property":
                    try {
                        NamedNodeMap attributes = node.getAttributes();
                        System.out.println("------------------------------------");
                        System.out.println(attributes.getNamedItem("name"));
                        System.out.println(attributes.getNamedItem("ref"));
                        System.out.println(attributes.getNamedItem("value"));
                        System.out.println("************************************");
                        Node name = attributes.getNamedItem("name");
                        Node ref = attributes.getNamedItem("ref");
                        Node value = attributes.getNamedItem("value");
                        if (null!=ref){
                            System.out.println(name.getNodeValue());
                            System.out.println(name.getLocalName());
                            /**
                             * builder.addPropertyValue 给bean中某一个字段赋值
                             *
                             * builder.addPropertyReference 给bean中一个对象字段赋值已存在于bean容器的对象，只需填写对象的ID即可赋值
                             */
                            /*方式一*/
                            builder.addPropertyReference(name.getNodeValue(),ref.getNodeValue());
                            /*方式二*/
//                          builder.addPropertyValue(name.getNodeValue(),parserContext.getRegistry().getBeanDefinition(ref.getNodeValue()));
                        }else{
                            builder.addPropertyValue(name.getNodeValue(),value.getNodeValue());
                        }

//                        Class cls = Class.forName(element.getAttribute("class"));
//                        Object obj = cls.newInstance();
//                        Field field = cls.getDeclaredField(node.getNodeName());
//                        PropertyDescriptor pd = new PropertyDescriptor(field.getName(),cls);
//                        Method method = pd.getWriteMethod();
//                        method.invoke(obj,node.getNodeValue());

//                        builder.addPropertyValue(node.getNodeName(),node.getNodeValue());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }

//        builder.addPropertyValue(element.getAttribute("id")+"123",child);
//        builder.addPropertyValue("id",element.getAttribute("id"));
//        builder.addPropertyValue("cls",element.getAttribute("class"));
    }
}

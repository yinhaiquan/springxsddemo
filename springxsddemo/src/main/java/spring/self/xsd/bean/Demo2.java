package spring.self.xsd.bean;

/**
 * @title : 自定义spring bean标签
 * @describle :
 *
 *    其实xsd就是对xml进行解析，这里的bean目录下的Child/Demo/Demo2等都是盛装xsd标签对象
 *    也可以自定义盛装对象，例如Demo2标签定义就是类似spring bean自动注入标签实现demo
 *
 *    Demo2Parser 重写getBeanClass方法返回自定义class
 *                重写doParse方法实例化class对象
 *
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/26 16:41 星期二
 */
public class Demo2 {
    private String id;
    private String cls;

    public Demo2() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }
}

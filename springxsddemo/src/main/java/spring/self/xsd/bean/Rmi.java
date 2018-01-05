package spring.self.xsd.bean;

/**
 * @title :
 * @describle : 测试多xsd模块定义
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/25 15:51 星期一
 */
public class Rmi {
    private String itemId;
    private String id;
    private Class cls;
    private String scope;
    private String init;

    public Rmi() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    @Override
    public String toString() {
        return "Rmi{" +
                "itemId='" + itemId + '\'' +
                ", id='" + id + '\'' +
                ", cls=" + cls +
                ", scope='" + scope + '\'' +
                ", init='" + init + '\'' +
                '}';
    }
}

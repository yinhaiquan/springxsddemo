package spring.self.xsd.bean;

/**
 * @title : 标签facade定义实体类
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/23 14:58 星期六
 */
public class Facade {
    private String id;
    private String interCode;
    private String reqEvent;
    private String resEvent;
    private String characterEncoding;
    private int money;
    private String birthday;
    private Child child;

    public Facade() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterCode() {
        return interCode;
    }

    public void setInterCode(String interCode) {
        this.interCode = interCode;
    }

    public String getReqEvent() {
        return reqEvent;
    }

    public void setReqEvent(String reqEvent) {
        this.reqEvent = reqEvent;
    }

    public String getResEvent() {
        return resEvent;
    }

    public void setResEvent(String resEvent) {
        this.resEvent = resEvent;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Facade{" +
                "id='" + id + '\'' +
                ", interCode='" + interCode + '\'' +
                ", reqEvent='" + reqEvent + '\'' +
                ", resEvent='" + resEvent + '\'' +
                ", characterEncoding='" + characterEncoding + '\'' +
                ", money=" + money +
                ", birthday='" + birthday + '\'' +
                ", child=" + child +
                '}';
    }
}

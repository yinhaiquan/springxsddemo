package aop;

import annotation.*;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @title :
 * @describle :  若要继承父类，父类属性字段必须声明为public类型，且父类不必添加@Validation注解
 * <p>
 * Create By yinhaiquan
 * @date 2017/12/27 14:35 星期三
 */
public class TestFrom extends Pager implements Serializable {

    @NotNull
    @Length(12)
    private long id;
    @NotNull
    @NotZero
    @Parttern(regexp = "^[\\s]+$")
    @Length(min = 5,max = 10,message = "该字段长度必须是5位")
    private String name;

    public TestFrom() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestFrom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(String.format("sdfabadf%s","sdfsdf"));
        Class cls = TestFrom.class;
        Field [] fs1 = cls.getFields();
        Field [] fs2 = cls.getDeclaredFields();
        for (Field f : fs1) {
            System.out.println(f.getName());
        }

        System.out.println("----------------------------");
        for (Field f : fs2) {
            System.out.println(f.getName());
        }
    }
}

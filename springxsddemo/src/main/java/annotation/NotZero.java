package annotation;

import java.lang.annotation.*;

/**
 * @title : 非零
 * @describle :
 * <p>
 *      <b>note:</b>
 *      该注解主要使用于整型字段是否为0判断
 * </p>
 * Create By yinhaiquan
 * @date 2018/1/2 10:13 星期二
 */
@Target({ElementType.METHOD,ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotZero {
    String message() default "该字段不能为零";
}

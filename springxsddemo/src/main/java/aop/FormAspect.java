package aop;

import annotation.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @title : 自定义bean校验
 * @describle :
 * <p>
 *      <b>note:</b>
 *      前提：校验bean必须继承validBase类
 *      使用方式：
 *              1. 配置扫描切面
 *              2. 指定校验方法上添加@Validation
 * </p>
 * Create By yinhaiquan
 * @date 2017/9/11 16:33 星期一
 */
@Aspect
@Component
public class FormAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private static String pointcut;

    /**
     * 方式一:
     * 设置包路径颗粒扫描切面
     * 粒度比方式二大，方式二只需要再需要验证的方法上添加注解即可
     *
     * 注意：默认控制层包路径必须包含controller或者control包名即可扫描到
     */
    @Pointcut("execution(public * *..*.controller..*.*(..))||" +
              "execution(public * *..*.control..*.*(..)) || " +
              "execution(public * *.control..*.*(..)) || " +
              "execution(public * *.controller..*.*(..)) || " +
              "execution(public * *controller..*.*(..)) || " +
              "execution(public * *control..*.*(..))")
    public void form() {
    }

    /**
     * 方式二：
     * 设置方法颗粒注解扫描切面
     * eg：使用时可在controller层方法上添加@Validation
     */
    @Pointcut("@annotation(annotation.Validation)")
    public void form2() {
    }

    /**
     * request 请求form过滤验证
     * @param joinPoint
     */
    @Before("form2()||form()")
    public void doBefore(JoinPoint joinPoint) throws IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {
        /*获取方法中一些信息*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println("执行方法名：");
        System.out.println(signature.getMethod());
        System.out.println("执行参数列表：");
        System.out.println(joinPoint.getArgs());
        Object [] objs = joinPoint.getArgs();
        for (Object obj:objs) {
            Class cls = obj.getClass();
            /*是否存在验证标签，判断是否需要验证*/
            if (null==cls.getAnnotation(Validation.class)){
                continue;
            }
            /*验证子类所有属性*/
            Field [] fields = cls.getDeclaredFields();
            validation(fields,obj,cls);
            /*验证父类所有public属性*/
            Field [] parentFields = cls.getFields();
            validation(parentFields,obj,cls);
        }
    }

    /**
     * 校验属性字段
     * @param fields
     * @param obj
     * @param cls
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void validation(Field [] fields,Object obj,Class cls) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (Field f: fields) {
            Annotation [] ans = f.getAnnotations();
            if (null==ans || ans.length==0){
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(),cls);
            Method method = pd.getReadMethod();
            Object paramVal = method.invoke(obj);
            for (Annotation an : ans) {
                if (an instanceof NotNull){
                    if (null==paramVal||"".equals(paramVal)){
                        String message = ((NotNull) an).message();
                        System.out.println(message);
                        System.out.println(f.getName()+"参数不能为空");
                    }
                } else if (an instanceof Parttern){
                    Parttern p = (Parttern) an;
                    /*或者p.value()*/
                    String partternVal = null==p.regexp()?p.value():p.regexp();
                    Pattern pattern = Pattern.compile(partternVal);
                    Matcher matcher = pattern.matcher(null!=paramVal?paramVal.toString():"");
                    if (!matcher.matches()){
                        String message = ((Parttern) an).message();
                        System.out.println(message);
                        System.out.println(f.getName()+"参数与正则表达式【"+partternVal+"】不匹配");
                    }
                } else if (an instanceof NotZero){
                    if (null!=paramVal &&!"".equals(paramVal) && "0".equals(paramVal)){
                        String message = ((NotZero) an).message();
                        System.out.println(message);
                        System.out.println(f.getName()+"参数不能为0");
                    }
                } else if (an instanceof Length){
                    if (null==paramVal||"".equals(paramVal)){
                        System.out.println("参数不能为空");
                    } else {
                        Length length = (Length) an;
                        if(0!=length.value()){
                            /* = value */
                            if (paramVal.toString().length() != length.value()){
                                System.out.println("参数长度不等于"+length.value());
                            }
                        }else if (0!=length.min()&&length.max()==Long.MAX_VALUE){
                            /* min ~ + */
                            if (length.min()>paramVal.toString().length()){
                                System.out.println("参数最小长度是"+length.min());
                            }
                        } else if (0==length.min()&&length.max()!=Long.MAX_VALUE){
                            /* 0 ~ max */
                            if (paramVal.toString().length()>length.max()){
                                System.out.println("参数最大长度是"+length.max());
                            }
                        } else {
                            /* min ~ max */
                            if (length.min()>paramVal.toString().length()||
                                    paramVal.toString().length()>length.max()){
                                System.out.println(String.format("参数长度必须是%d~%d范围内",length.min(),length.max()));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * response请求form过滤验证
     * @param object
     */
    @AfterReturning(pointcut = "form2()||form()", returning = "object")
    public void doAfterReturning(Object object) {
        System.out.println(object.toString());
        System.out.println("end");
    }
}

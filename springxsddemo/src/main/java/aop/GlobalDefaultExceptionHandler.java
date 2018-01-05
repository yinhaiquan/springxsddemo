package aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @title : 全局异常捕捉处理
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2017/5/24 18:38 星期三
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    public static Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /*
    *
    *可配置多个不同异常捕捉处理器
    */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String illegalOptaionHandler(Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        //可在此处配置捕捉异常信息处理，包括参数不匹配等杂七杂八的异常信息统一处理

        System.out.println(e.getMessage());
        return e.getMessage();
    }
}

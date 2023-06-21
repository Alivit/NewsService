package ru.clevertec.logging.aop.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CustomPointcut {

    @Pointcut("(within(@org.springframework.web.bind.annotation.RestController *) " +
            "|| within(@org.springframework.stereotype.Controller *))" +
            "&& within(@ru.clevertec.logging.aop.annotation.Logging *)")
    public void isControllerLayer(){}

    @Pointcut("within(@ru.clevertec.logging.aop.annotation.Logging *)" +
            "&& within(@org.springframework.stereotype.Service *)")
    public void isServiceLayer(){}

    @Pointcut("within(@ru.clevertec.logging.aop.annotation.Logging *)" +
            "&& within(ru.clevertec.*.service.proxy.*Proxy)")
    public void isProxyLayer(){}

    @Pointcut("within(@ru.clevertec.logging.aop.annotation.Logging *) " +
            "&& within(ru.clevertec.*.service.*Service)")
    public void isCustomServiceLayer(){}

    @Pointcut("within(@ru.clevertec.logging.aop.annotation.Logging *) " +
            "&& within(ru.clevertec.*.controller.*Controller)")
    public void isCustomControllerLayer(){}
}

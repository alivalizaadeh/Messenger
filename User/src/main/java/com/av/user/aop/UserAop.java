package com.av.user.aop;

import com.av.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class UserAop {
    @After("execution(public  * insert())")
    public void logSaveUser(JoinPoint joinPoint){

    }

}

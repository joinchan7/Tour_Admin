package com.chan.ssm.controller;

import com.chan.ssm.domain.SysLog;
import com.chan.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

// @Aspect
@Component
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    // 开始访问时间
    private Date visitTime;
    // 访问的类
    private Class<?> clazz;
    // 访问的方法
    private Method method;

    @Pointcut("execution(* com.chan.ssm.controller.*.*(..))")
    private void pt1() {
    }

    @Before("pt1()")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        // 获取执行方法method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
            Class<?>[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
            // bug
            System.out.println(method);
        }
    }

    @After("pt1()")
    public void doAfter() {
        // 访问时长
        long time = new Date().getTime() - visitTime.getTime();

        if (clazz != null && method != null && clazz != LogAop.class) {
            // 获取类上的Mapping
            RequestMapping classAnnotation = clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                // 获取方法上的Mapping
                String url;
                String[] methodValue = new String[0];
                GetMapping getAnnotation = method.getAnnotation(GetMapping.class);
                PostMapping postAnnotation = method.getAnnotation(PostMapping.class);
                PutMapping putAnnotation = method.getAnnotation(PutMapping.class);
                DeleteMapping deleteAnnotation = method.getAnnotation(DeleteMapping.class);
                if (getAnnotation != null) {
                    methodValue = getAnnotation.value();
                }
                if (postAnnotation != null) {
                    methodValue = postAnnotation.value();
                }
                if (putAnnotation != null) {
                    methodValue = putAnnotation.value();
                }
                if (deleteAnnotation != null) {
                    methodValue = deleteAnnotation.value();
                }
                url = classValue[0] + methodValue[0];

                // 获取访问的IP
                String ip = request.getRemoteAddr();

                // 获取当前操作的用户
                SecurityContext context = SecurityContextHolder.getContext();   // 从上下文中获取当前登录的用户对象
                User user = (User) context.getAuthentication().getPrincipal();
                String username = user.getUsername();

                // 将日志相关信息封装在SysLog 对象
                SysLog sysLog = new SysLog();
                sysLog.setExecutionTime(time);
                sysLog.setIp(ip);
                sysLog.setMethod("[类名] " + clazz.getName() + " [方法名] " + method.getName());
                sysLog.setUrl(url);
                sysLog.setUsername(username);
                sysLog.setVisitTime(visitTime);

                System.out.println(sysLog);

                // 调用Service完成操作
                if (!clazz.getName().equals("com.hz.controller.SysLogController")) {
                    sysLogService.save(sysLog);
                }

            }
        }
    }
}

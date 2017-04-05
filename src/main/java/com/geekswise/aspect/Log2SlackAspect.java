package com.geekswise.aspect;

import com.geekswise.configuration.SlackConfiguration;
import com.geekswise.slack.SlackNotification;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Ahn on 2017. 4. 5..
 */
@Aspect
@Component
@Slf4j
public class Log2SlackAspect {

    @Autowired
    private SlackNotification slackNotification;

    @Autowired
    private SlackConfiguration slackConfiguration;

    @Around("@annotation(Log2Slack)")
    public Object log2Slack(ProceedingJoinPoint joinPoint) throws Throwable {

        /** 대상 method 실행 전 실행 구역 **/
        slackNotification.notification(slackConfiguration,
                new SlackNotification.SlackMessage("ENTER method : " +
                        joinPoint.getSignature().getDeclaringTypeName() + "." +joinPoint.getSignature().getName(),
                        "arguments[s] = " + Arrays.toString(joinPoint.getArgs())));
        log.info("### Enter : {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        /** 대상 method 실행 전 실행 구역 종료 **/

        try {
            Object result = joinPoint.proceed();
            /** 대상 method 실행 후 끼어들어 실행할 로직을 작성, 리턴 값을 가공할 수 있다. **/
            slackNotification.notification(slackConfiguration,
                    new SlackNotification.SlackMessage("EXIT method : " +
                            joinPoint.getSignature().getDeclaringTypeName() + "." +joinPoint.getSignature().getName(),
                            "result = " + result));
            log.info("### Exit : {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }

    }

}

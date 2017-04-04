package com.geekswise.configuration;

import com.geekswise.aspect.ExecutionTimeLogAspect;
import com.geekswise.aspect.Log2SlackAspect;
import com.geekswise.aspect.LoggingAspect;
import com.geekswise.slack.SlackNotification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * Created by Ahn on 2017. 4. 5..
 */
@Configuration
@EnableAspectJAutoProxy
public class Log2SlackAppConfiguration {

    @Bean
    SlackConfiguration slackConfiguration() {
        return new SlackConfiguration();
    }

    @Bean
    public SlackNotification slackNotification() {
        return new SlackNotification();
    }

    @Bean
    public AsyncRestTemplate asyncRestTemplate() {
        return new AsyncRestTemplate();
    }

    @Bean
    public Log2SlackAspect log2Slack() {
        return new Log2SlackAspect();
    }

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

    @Bean
    public ExecutionTimeLogAspect executionTimeLogAspect() {
        return new ExecutionTimeLogAspect();
    }

}

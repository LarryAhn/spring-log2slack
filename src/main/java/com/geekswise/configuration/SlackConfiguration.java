package com.geekswise.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ahn on 2017. 4. 5..
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "slack")
public class SlackConfiguration {
    private String webHookUrl;
    private String channel;
}

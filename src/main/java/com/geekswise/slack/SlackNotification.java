package com.geekswise.slack;

import com.geekswise.configuration.SlackConfiguration;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.List;

/**
 * Created by Ahn on 2017. 4. 5..
 */
@Service
@Slf4j
public class SlackNotification {

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SlackMessage {
        private String title;
        private String text;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Slack {
        private String text;
        private String channel;
        private List<SlackMessage> attachments = Lists.newArrayList();

        void addAttachment(SlackMessage attachment) {
            this.attachments.add(attachment);
        }
    }

    /**
     * notification
     *
     * @param configuration
     * @param message
     * @return
     */
    @Async
    public void notification(SlackConfiguration configuration, SlackMessage message)
            throws InterruptedException {
        Slack slack = Slack.builder().channel(configuration.getChannel())
                .attachments(Lists.newArrayList(message)).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Slack> requestEntity = new HttpEntity<>(slack, headers);

        ListenableFuture<ResponseEntity<String>> entity =
                asyncRestTemplate.exchange(configuration.getWebHookUrl(),
                        HttpMethod.POST, requestEntity, String.class);
        entity.addCallback(result -> {
        }, ex -> System.out.println(ex.getStackTrace()));
    }
}

package br.com.fiaplanchesorder.infra.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Value("${kafka.topic.name}")
    private String topicPayment;

    @Bean
    public NewTopic fiapLanchesPayment() {
        return TopicBuilder.name(topicPayment)
                .build();
    }
}

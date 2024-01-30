package br.com.fiaplanchesorder.infra.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Value("${kafka.topic.producer.name}")
    private String topicPayment;

    @Value("${kafka.topic.consumer.name}")
    private String topicOrder;

    @Bean
    public NewTopic topicPayment() {
        return TopicBuilder.name(topicPayment)
                .build();
    }

    @Bean
    public NewTopic topicOrder() {
        return TopicBuilder.name(topicOrder)
                .build();
    }
}

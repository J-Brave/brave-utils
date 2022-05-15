package com.brave.bootconfigs.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Qiang.cao
 * Date: 2022/5/15
 * Time: 22:09
 * Description:
 */
@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${spring.secondKafka.bootstrap-servers}")
    private String secondServers;
    @Value("${spring.secondKafka.consumer.group-id}")
    private String secondGroupId;
    @Value("${spring.secondKafka.consumer.enable-auto-commit}")
    private String secondEnableAutoCommit;


    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> secondKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(secondConsumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public KafkaTemplate<String, String> secondKafkaTemplate() {
        return new KafkaTemplate<String, String>(secondProducerFactory());
    }
    @Bean
    public ProducerFactory<String, String> secondProducerFactory() {
        return new DefaultKafkaProducerFactory<>(secondProducerConfig());
    }

    private Map<String, Object> secondProducerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, secondServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

    @Bean
    public ConsumerFactory<Integer, String> secondConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(secondConsumerConfig());
    }

    private Map<String, Object> secondConsumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, secondServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, secondGroupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, secondEnableAutoCommit);
        return props;
    }

}

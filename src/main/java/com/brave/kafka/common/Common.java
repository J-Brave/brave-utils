package com.brave.kafka.common;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author jbrave
 */
public class Common {

    public static final String brokerList = "localhost:9092";
    public static final String topic = "brave-demo";
    public static final String groupId = "group.demo";

    public static Properties getConsumerProperties() {
        Properties properties = new Properties();
        //设置key的反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //设置值的反序列化器
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, Common.groupId);
        return properties;
    }

    public static Properties getProducerProperties() {
        Properties properties = new Properties();
        //设置key序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //设置值的序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //指定broker地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        return properties;
    }

}

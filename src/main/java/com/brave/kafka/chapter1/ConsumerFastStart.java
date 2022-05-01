package com.brave.kafka.chapter1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author jbrave
 */
public class ConsumerFastStart {
    private static final String brokerList = "101.35.228.171:9092";
    private static final String topic = "topic-demo";
    private static final String groupId = "group.demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        //设置key的反序列化
        //properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //设置value的反序列化
        //properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //设置集群地址
        //properties.put("bootstrap.servers", brokerList);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);

        //设置消费组
        //properties.put("group.id", groupId);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        //消息的接受
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Collections.singleton(topic));

        //监听topic
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }
    }

}

package com.brave.kafka.chapter1;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author jbrave
 */
@Slf4j
public class ProducerFastStart {
    private static final String brokerList = "localhost:9092";
    private static final String topic = "brave-demo";

    public static void main(String[] args) {

        Properties properties = new Properties();

        //设置Key序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //设置重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);

        //设置值序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //设置集群地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);

        //指定自定义拦截器
        //properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorPreFix.class.getName());

        //发送消息
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String ,String> record = new ProducerRecord<>(topic, "kafka-demo", "hello, kafka!");
        try {
            //同步发送
            //Future<RecordMetadata> send = producer.send(record);
            //RecordMetadata metadata = send.get();
            //log.info("同步发送 - topic:{}, partition:{}, offset:{}", metadata.topic(), metadata.partition(), metadata.offset());

            //异步发送
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e == null) {
                        log.info("异步发送 - topic:{}, partition:{}, offset:{}", metadata.topic(), metadata.partition(), metadata.offset());
                    }
                }
            });
        } catch (Exception e) {
            log.error("生产者发送消息异常:", e);
        }
        producer.close();
    }

}

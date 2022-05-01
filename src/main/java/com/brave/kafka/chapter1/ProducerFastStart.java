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
    private static final String brokerList = "101.35.228.171:9092";
    private static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        //设置key序列化器
        //properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //设置重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);

        //设置值序列化器
        //properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //设置集群地址
        //properties.put("bootstrap.servers", brokerList);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);

        //消息发送
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        //封装消息发送对象
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "kafka-demo", "hello,brave!");
        try {

            //Future<RecordMetadata> recordMetadata = producer.send(record);
            //log.info("topic:{}, partition:{}, offset:{}", recordMetadata.get().topic(), recordMetadata.get().partition(), recordMetadata.get().offset());

            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        log.info("topic:{}, partition:{}, offset:{}", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
                    }
                }
            });
        } catch (Exception e) {
            log.error("异常:", e);
        }
        producer.close();
    }

}

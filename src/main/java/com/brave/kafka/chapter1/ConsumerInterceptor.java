package com.brave.kafka.chapter1;

import com.brave.kafka.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author: Qiang.cao
 * Date: 2022/5/14
 * Time: 12:47
 * Description:
 */
@Slf4j
public class ConsumerInterceptor {

    public static void main(String[] args) {
        Properties prop = Common.getConsumerProperties();

        //指定消费者拦截器
        prop.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, ConsumerInterceptorTTL.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);

        consumer.subscribe(Collections.singleton(Common.topic));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                log.info("consumer message:{}, partition:{}", record.value(), record.partition());
            }
        }
    }
}

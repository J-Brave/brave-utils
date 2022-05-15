package com.brave.kafka.chapter1;

import com.brave.kafka.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

/**
 * @author: Qiang.cao
 * Date: 2022/5/14
 * Time: 11:54
 * Description:
 */
@Slf4j
public class SeekDemo {

    public static void main(String[] args) {
        Properties properties = Common.getConsumerProperties();
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, Common.groupId);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Collections.singleton(Common.topic));

        consumer.poll(Duration.ofMillis(2000));

        Set<TopicPartition> assignment = consumer.assignment();
        log.info("当前分区: {}", assignment);
        for (TopicPartition tp : assignment) {
            consumer.seek(tp, 10);
        }

        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(2000));
            for (ConsumerRecord<String, String> record : consumerRecords) {
                log.info("offset: {} - value: {}", record.offset(), record.value());
            }
        }
    }
}

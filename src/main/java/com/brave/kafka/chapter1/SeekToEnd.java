package com.brave.kafka.chapter1;

import com.brave.kafka.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

/**
 * @author: Qiang.cao
 * Date: 2022/5/14
 * Time: 12:06
 * Description:
 */
@Slf4j
public class SeekToEnd {

    public static void main(String[] args) {
        Properties properties = Common.getConsumerProperties();
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, Common.groupId);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Collections.singleton(Common.topic));

        Set<TopicPartition> assignment = new HashSet<>();
        while (assignment.size() == 0) {
            consumer.poll(Duration.ofMillis(2000));
            assignment = consumer.assignment();
        }

        Map<TopicPartition, Long> offsets = consumer.endOffsets(assignment);
        for (TopicPartition tp : assignment) {
            consumer.seek(tp, offsets.get(tp) + 1);
        }

        log.info("assignmeng: {} - offsets: {}", assignment, offsets);

        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(2000));
            for (ConsumerRecord<String, String> record : consumerRecords) {
                log.info("offset: {} - value: {}", record.offset(), record.value());
            }
        }
    }
}


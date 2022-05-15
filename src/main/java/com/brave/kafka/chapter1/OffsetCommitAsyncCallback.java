package com.brave.kafka.chapter1;


import com.brave.kafka.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jbrave
 * 异步提交
 */
@Slf4j
public class OffsetCommitAsyncCallback {

    private static AtomicBoolean running = new AtomicBoolean(true);
    public static void main(String[] args) {
        Properties properties = Common.getConsumerProperties();

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Collections.singleton(Common.topic));

        try {
            while (running.get()) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    log.info("consumer message is : {}", consumerRecord.value());
                }
                consumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                        if (e == null) {
                            log.info("success commit offset: {}", map);
                        } else {
                            log.error("fail to commit offsets {}", map, e);
                        }
                    }
                });
            }
        } finally {
            consumer.close();
        }

        try {
            while (running.get()) {
                consumer.commitAsync();;
            }
        } finally {

        }
    }
}

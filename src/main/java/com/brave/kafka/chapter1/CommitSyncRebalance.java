package com.brave.kafka.chapter1;

import com.brave.kafka.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jbrave
 * 再均衡
 */
@Slf4j
public class CommitSyncRebalance {

    private static final AtomicBoolean isRunning = new AtomicBoolean(true);
    public static void main(String[] args) {

        Properties properties = Common.getConsumerProperties();

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();

        consumer.subscribe(Collections.singleton(Common.topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                //尽量避免重复消费
                consumer.commitSync(currentOffsets);
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                //do nothing
            }
        });

        try {
            while (isRunning.get()) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : consumerRecords) {
                    log.info("offset:{} - value: {}", record.offset(), record.value());
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1));
                }
                consumer.commitAsync(currentOffsets, null);
            }
        } finally {
            consumer.close();
        }
    }

}

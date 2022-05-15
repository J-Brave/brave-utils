package com.brave.kafka.chapter1;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jbrave
 * 消费者拦截器
 */
@Slf4j
public class ConsumerInterceptorTTL implements ConsumerInterceptor<String, String> {

    private static final long EXPIRE_INTERVAL = 10 * 1000;
    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> consumerRecords) {
        log.info("before: {}", consumerRecords);
        long now = System.currentTimeMillis();
        Map<TopicPartition, List<ConsumerRecord<String, String>>> newRecords = new HashMap<>();
        for (TopicPartition tp : consumerRecords.partitions()) {
            List<ConsumerRecord<String, String>> tpRecords = consumerRecords.records(tp);
            List<ConsumerRecord<String, String>> newTpRecords = new ArrayList<>();
            for (ConsumerRecord<String, String> record : tpRecords) {
                if (now - record.timestamp() < EXPIRE_INTERVAL) {
                    newTpRecords.add(record);
                }
            }

            if (!newTpRecords.isEmpty()) {
                newRecords.put(tp, newTpRecords);
            }

        }
        return new ConsumerRecords<>(newRecords);
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> map) {
        //map.forEach((tp, offset) -> log.info("tp:{} - offset:{}", tp, offset.offset()));
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}

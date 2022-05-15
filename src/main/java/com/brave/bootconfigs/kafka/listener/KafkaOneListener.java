package com.brave.bootconfigs.kafka.listener;

import com.brave.bootconfigs.kafka.common.KafkaCommon;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: Qiang.cao
 * Date: 2022/5/15
 * Time: 21:38
 * Description:
 */

@Slf4j
@Component
public class KafkaOneListener {

    @KafkaListener(topics = KafkaCommon.topic_one, groupId = KafkaCommon.group_one)
    public void oneListener(ConsumerRecord<String, String> record) {
        log.info("oneListener receive Message: {}", record.value());
    }

    @KafkaListener(topics = KafkaCommon.topic_two, groupId = KafkaCommon.group_two)
    public void twoListener(ConsumerRecord<String, String> record) {
        log.info("oneListener receive Message: {}", record.value());
    }
}

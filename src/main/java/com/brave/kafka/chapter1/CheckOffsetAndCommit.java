package com.brave.kafka.chapter1;

import com.brave.kafka.common.Common;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author jbrave
 * 同步提交
 */
@Slf4j
public class CheckOffsetAndCommit {

    public static void main(String[] args) {

        Properties properties = new Properties();

        //设置Key反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //设置值反序列化器
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //设置服务器
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Common.brokerList);

        //设置消费组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, Common.groupId);

        //设置手动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        //构建消息对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        //指定消费topic下的partition
        TopicPartition tp = new TopicPartition(Common.topic, 0);
        consumer.assign(Arrays.asList(tp));

        //监听消息
        long lastConsumerOffset = -1;
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            if (consumerRecords.isEmpty()) {
                break;
            }
            List<ConsumerRecord<String, String>> partitionConsumer = consumerRecords.records(tp);
            lastConsumerOffset = partitionConsumer.get(partitionConsumer.size() -1).offset();
            //设置同步提交
            consumer.commitAsync();
        }
        log.info("consumed offset is : {}", lastConsumerOffset);
        OffsetAndMetadata offsetAndMetadata = consumer.committed(tp);
        log.info("committed offset is : {}", offsetAndMetadata.offset());
        long position = consumer.position(tp);
        log.info("the offset of the next record is : {}", position);
    }
}

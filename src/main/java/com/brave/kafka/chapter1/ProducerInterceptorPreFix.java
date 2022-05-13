package com.brave.kafka.chapter1;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author: Qiang.cao
 * Date: 2022/5/13
 * Time: 23:43
 * Description:
 */
public class ProducerInterceptorPreFix implements ProducerInterceptor<String, String> {
    private volatile long sendSuccess = 0;
    private volatile long sendFail = 0;
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        String modelValue = "prefix_" + producerRecord.value();
        return new ProducerRecord<>(producerRecord.topic(), producerRecord.partition(), producerRecord.timestamp(),
                producerRecord.key(), modelValue, producerRecord.headers());
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null) {
            sendSuccess++;
        } else {
            sendFail++;
        }
    }

    @Override
    public void close() {
        double ratio = (double) sendSuccess / (sendFail + sendSuccess);
        System.out.println("[INFO] 发送成功率：" + ratio + "%");
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}

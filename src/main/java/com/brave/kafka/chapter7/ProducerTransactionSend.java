package com.brave.kafka.chapter7;

import com.brave.kafka.common.Common;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author jbrave
 * 事务
 */
public class ProducerTransactionSend {

    public static final String transactionId = "transactionId";

    public static void main(String[] args) {

        Properties properties = Common.getProducerProperties();

        //开启幂等性
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        //配置transactionId
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionId);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // 初始化事务
        producer.initTransactions();
        // 开启事务
        producer.beginTransaction();

        try {
            // 处理业务逻辑
            ProducerRecord<String, String> record1 = new ProducerRecord<>(Common.topic, "message-1");
            producer.send(record1);

            int i = 1 / 0;

            ProducerRecord<String, String> record2 = new ProducerRecord<>(Common.topic, "message-2");
            producer.send(record2);

            ProducerRecord<String, String> record3 = new ProducerRecord<>(Common.topic, "message-3");
            producer.send(record3);

            //事务提交
            producer.commitTransaction();
        } catch (Exception e) {
            //事务回滚
            producer.abortTransaction();
            e.printStackTrace();
        }

        producer.close();
    }
}

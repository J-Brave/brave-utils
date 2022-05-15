package com.brave.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Qiang.cao
 * Date: 2022/5/15
 * Time: 11:01
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
public class controller {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static final String topic = "brave";

    @GetMapping("/send/{input}")
    public String sendToKafka(@PathVariable String input) {
        //kafkaTemplate.send(topic, input);

        //事务的支持
        kafkaTemplate.executeInTransaction(t -> {
            t.send(topic, input);

            t.send(topic, input + " other");
            if ("error".equals(input)) {
                throw new RuntimeException("input is error");
            }
            return true;
        });
        return "Send Success";
    }

    @GetMapping("/send2/{input}")
    @Transactional(rollbackFor = RuntimeException.class)
    public String sendToKafkaTran(@PathVariable String input) {
        kafkaTemplate.send(topic, input);
        if ("error".equals(input)) {
            throw new RuntimeException("input is error");
        }
        kafkaTemplate.send(topic, input+  "other");

        return "Send Success";
    }

    @KafkaListener(id = "", topics = topic, groupId = "group.demo")
    public void listener(String input) {
        log.info("Kafka listener message ： {}", input);
    }
}

package com.brave.bootconfigs.kafka.controller;

import com.brave.bootconfigs.kafka.common.KafkaCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Qiang.cao
 * Date: 2022/5/15
 * Time: 21:37
 * Description:
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> secondKafkaTemplate;

    @GetMapping("/sendMessageToBrokerOne/{message}")
    public String sendMessageToBrokerOne(@PathVariable String message) {
        kafkaTemplate.send(KafkaCommon.topic_one, message);
        return "sendMessageToBrokerOne Success";
    }

    @GetMapping("/sendMessageToBrokerTwo/{message}")
    public String sendMessageToBrokerTwo(@PathVariable String message) {
        secondKafkaTemplate.send(KafkaCommon.topic_two, message);
        return "sendMessageToBrokerTwo Success";
    }
}

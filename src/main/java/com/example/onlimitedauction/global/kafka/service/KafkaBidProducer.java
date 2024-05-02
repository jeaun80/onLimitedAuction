package com.example.onlimitedauction.global.kafka.service;


import com.example.onlimitedauction.global.kafka.dto.RequestSendBidDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaBidProducer {
    @Value("${spring.kafka.send-topic-name}")
    private String alertTopicName;
    @Value("${spring.kafka.accept-topic-name}")
    private String acceptTopicName;

    private final KafkaTemplate kafkaTemplate;

    public void sendBid(RequestSendBidDto dto) {
        Map<String, String> map = new HashMap<>();
//        map.put("line", dto.getId());
//        map.put("house", dto.getPrice());
        System.out.println("kafka dto = "+dto.getPrice()+ " "+ dto.getId());
        kafkaTemplate.send(alertTopicName, dto);
//        kafkaTemplate.send(alertTopicName, map);
    }

//    public void accept(AcceptDTO dto) {
//        Map<String, String> map = new HashMap<>();
////        map.put("line", dto.getLine());
////        map.put("accept", dto.getAcceptHouse());
////        map.put("target", dto.getTargetHouse());
//
//        kafkaTemplate.send(acceptTopicName, map);
//    }
}
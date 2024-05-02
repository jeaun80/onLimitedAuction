package com.example.onlimitedauction.global.kafka.controller;

import com.example.onlimitedauction.global.kafka.dto.RequestSendBidDto;
import com.example.onlimitedauction.global.kafka.service.KafkaBidProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kafka/bid")
public class producerController {

    private final KafkaBidProducer kafkaAlertProducer;

    @PostMapping()
    public void sendBid(@RequestBody RequestSendBidDto dto) {
        kafkaAlertProducer.sendBid(dto);
    }

//    @PostMapping("/accept")
//    public void accept(@RequestBody AcceptDTO dto) {
//        kafkaAlertProducer.accept(dto);
//    }
}

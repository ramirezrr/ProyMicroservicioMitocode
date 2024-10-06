package com.mitocode.licencias.util;

import com.mitocode.generic.GenericEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUtil {


    @Value("${kafka.mitocode.topicName:mitocode}")
    private String topicName;

    private final KafkaTemplate<String, GenericEntity<?>> kafkaTemplate;

    public void sendMessage(GenericEntity<?> message) {
        kafkaTemplate.send(topicName, message);
    }

}

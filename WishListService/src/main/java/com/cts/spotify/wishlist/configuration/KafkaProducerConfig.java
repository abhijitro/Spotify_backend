package com.cts.spotify.wishlist.configuration;


import com.cts.spotify.wishlist.dto.UserDetails;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, UserDetails> producerFactory(){
        Map<String,Object>  configProp=new HashMap<>();
        configProp.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        configProp.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProp.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProp);
    }

    @Bean
    public KafkaTemplate<String,UserDetails> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}

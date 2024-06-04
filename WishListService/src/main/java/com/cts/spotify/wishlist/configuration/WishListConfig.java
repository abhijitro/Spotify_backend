package com.cts.spotify.wishlist.configuration;

import com.cts.spotify.wishlist.aspects.PerformanceTrackerHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WishListConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry){
        observationRegistry.observationConfig().observationHandler(new PerformanceTrackerHandler());
        return new ObservedAspect(observationRegistry);
    }

    @Bean
    public NewTopic newTopic(){

        return TopicBuilder.name("SpotifyApp").build();
    }


}

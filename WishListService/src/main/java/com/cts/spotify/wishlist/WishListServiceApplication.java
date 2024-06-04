package com.cts.spotify.wishlist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@EnableMongoRepositories
@OpenAPIDefinition(info = @Info(title = "WishListService API", version = "1.0", description = "A wishlist for saving your favorite tracks from Spotify"))
@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
)
public class WishListServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishListServiceApplication.class, args);
	}

}

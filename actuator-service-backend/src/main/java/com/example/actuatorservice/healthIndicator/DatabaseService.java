package com.example.actuatorservice.healthIndicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class DatabaseService implements ReactiveHealthIndicator {

	private final String DATABASE_SERVICE="Databse Service";
	
    @Override
    public Mono<Health> health() {
        return checkDatabaseServiceHealth().onErrorResume(
          ex -> Mono.just(new Health.Builder().down(ex).withDetail(DATABASE_SERVICE,"Service is not available").build())
        );
    }

    private Mono<Health> checkDatabaseServiceHealth() {
        // we could use WebClient to check health reactively
        return Mono.just(new Health.Builder().up().withDetail(DATABASE_SERVICE,"Service is available").build());
    }
}

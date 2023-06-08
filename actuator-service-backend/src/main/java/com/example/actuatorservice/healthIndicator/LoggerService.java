package com.example.actuatorservice.healthIndicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class LoggerService implements ReactiveHealthIndicator {

	private final String LOGGER_SERVICE="Logger Service";
	
    @Override
    public Mono<Health> health() {
        return checkLoggerServiceHealth().onErrorResume(
          ex -> Mono.just(new Health.Builder().down(ex).withDetail(LOGGER_SERVICE,"Logger is not available").build())
        );
    }

    private Mono<Health> checkLoggerServiceHealth() {
        // we could use WebClient to check health reactively
        return Mono.just(new Health.Builder().up().withDetail(LOGGER_SERVICE,"Logger is available").build());
    }
}

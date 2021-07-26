//package com.travel.bus.config;
//
//import java.time.Duration;
//
//import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
//import org.springframework.cloud.client.circuitbreaker.Customizer;
//import org.springframework.context.annotation.Bean;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
//import io.github.resilience4j.timelimiter.TimeLimiterConfig;
//
//public class CircuitBreakConfig {
//	
//
//	@Bean
//	public Customizer<Resilience4JCircuitBreakerFactory> specificCustomConfiguration1() {
//
//	    // the circuitBreakerConfig and timeLimiterConfig objects
//		
//		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom().minimumNumberOfCalls(3)
//				  .failureRateThreshold(50)
//				  .waitDurationInOpenState(Duration.ofMillis(1000))
//				  .slidingWindowSize(2)				  
//				  .build();
//				TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//				  .timeoutDuration(Duration.ofSeconds(4))
//				  .build();
//
//	    return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
//	      .timeLimiterConfig(timeLimiterConfig).build(), "customerCircuitbreaker");
//	}
//
//}

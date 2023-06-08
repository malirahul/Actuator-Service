package com.example.actuatorservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.actuatorservice.dto.HealthDto;
import com.example.actuatorservice.dto.MetricsDto;
import com.example.actuatorservice.dto.ProcessUsageMetricsDto;
import com.example.actuatorservice.healthIndicator.DiskSpaceHealthIndicator;
import com.example.actuatorservice.service.ActuatorService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("http://localhost:3000")
@RestController
@Slf4j
@RequestMapping()
public class ActuatorController {
	@Autowired
	private ActuatorService service;

	@Autowired
	private HealthEndpoint healthEndpoint;

	@GetMapping("/health")
	public HealthDto health() {
		return service.getHealth();
	}

	@GetMapping("/metrics")
	public MetricsDto metrics() {
		return service.getMetrics();
	}

//    @GetMapping("/metrics/process.cpu.usage")
//    public String processUsageMetrics(){
//        return service.getProcessUsageMetrics();
//    }
	@GetMapping("/metrics/processCpuUsage")
	public ProcessUsageMetricsDto processUsageMetrics() {
		return service.getUsageMetrics();
	}

}

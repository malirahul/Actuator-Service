package com.example.actuatorservice.service;

import org.springframework.stereotype.Service;

import com.example.actuatorservice.dto.HealthDto;
import com.example.actuatorservice.dto.MetricsDto;
import com.example.actuatorservice.dto.ProcessUsageMetricsDto;

@Service
public interface ActuatorService {

    HealthDto getHealth();

    MetricsDto getMetrics();

	ProcessUsageMetricsDto getUsageMetrics();

	
}


package com.example.actuatorservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Component;

import com.example.actuatorservice.dto.HealthDto;
import com.example.actuatorservice.dto.MetricsDto;
import com.example.actuatorservice.dto.ProcessUsageMetricsDto;



@Slf4j
@Component
public class ActuatorServiceImpl implements ActuatorService{
    @Autowired
    MetricsEndpoint metricsEndpoint;
    @Autowired
    HealthEndpoint healthEndpoint;
    

    @Override
    public HealthDto getHealth() {
        HealthDto health = new HealthDto();
        health.setStatus(healthEndpoint.health().getStatus().toString());
        health.setDiskSpace(healthEndpoint.healthForPath("diskSpace").toString());
        return health;
    }

    @Override
    public MetricsDto getMetrics() {
        MetricsDto metrics = new MetricsDto();
        metrics.setApplicationReadyTime(metricsEndpoint.metric("application.ready.time",null).getMeasurements().get(0).getValue().toString());

        metrics.setApplicationStartedTime(metricsEndpoint.metric("application.started.time",null).getDescription() +
                metricsEndpoint.metric("application.started.time",null).getMeasurements().get(0).getValue());

        metrics.setDiskTotal(metricsEndpoint.metric("disk.total",null).getMeasurements().get(0).getValue().toString());

        metrics.setDiskFree(metricsEndpoint.metric("disk.free",null).getMeasurements().get(0).getValue().toString());

        metrics.setActiveExecutor(metricsEndpoint.metric("executor.active",null).getMeasurements().get(0).getValue().toString());

        metrics.setExecutorCompleted(metricsEndpoint.metric("executor.completed",null).getMeasurements().get(0).getValue().toString());

        metrics.setExecutorPoolCore(metricsEndpoint.metric("executor.pool.core",null).getMeasurements().get(0).getValue().toString());

        metrics.setExecutorPoolMax(metricsEndpoint.metric("executor.pool.max",null).getMeasurements().get(0).getValue().toString());

        metrics.setExecutorPoolSize(metricsEndpoint.metric("executor.pool.size",null).getMeasurements().get(0).getValue().toString());

        metrics.setExecutorQueueRemaining(metricsEndpoint.metric("executor.queue.remaining",null).getMeasurements().get(0).getValue().toString());
        metrics.setExecutorQueued(metricsEndpoint.metric("executor.queued",null).getMeasurements().get(0).getValue().toString());

        metrics.setHttpServerRequests(metricsEndpoint.metric("http.server.requests",null).getMeasurements().toString());
        metrics.setHttpServerRequestsActive(metricsEndpoint.metric("http.server.requests.active",null).getMeasurements().toString());
        metrics.setJvmBufferCount(metricsEndpoint.metric("jvm.buffer.count",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmBufferMemoryUsed(metricsEndpoint.metric("jvm.buffer.memory.used",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmBufferTotalCapacity(metricsEndpoint.metric("jvm.buffer.total.capacity",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmClassesLoaded(metricsEndpoint.metric("jvm.classes.loaded",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmClassesUnloaded(metricsEndpoint.metric("jvm.classes.unloaded",null).getMeasurements().get(0).getValue().toString());

        metrics.setJvmCompilationTime(metricsEndpoint.metric("jvm.compilation.time",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmGcLiveDataSize(metricsEndpoint.metric("jvm.gc.live.data.size",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmGcMaxDataSize(metricsEndpoint.metric("jvm.gc.max.data.size",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmGcMemoryAllocated(metricsEndpoint.metric("jvm.gc.memory.allocated",null).getMeasurements().get(0).getValue().toString());


        metrics.setJvmGcMemoryPromoted(metricsEndpoint.metric("jvm.gc.memory.promoted",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmGcOverhead(metricsEndpoint.metric("jvm.gc.overhead",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmGcPause(metricsEndpoint.metric("jvm.gc.pause",null).getMeasurements().toString());
        metrics.setJvmInfo(metricsEndpoint.metric("jvm.info",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmMemoryCommitted(metricsEndpoint.metric("jvm.memory.committed",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmMemoryMax(metricsEndpoint.metric("jvm.memory.max",null).getMeasurements().get(0).getValue().toString());

        metrics.setJvmMemoryUsageAfterGc(metricsEndpoint.metric("jvm.memory.usage.after.gc",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmMemoryUsed(metricsEndpoint.metric("jvm.memory.used",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmThreadsDaemon(metricsEndpoint.metric("jvm.threads.daemon",null).getMeasurements().get(0).getValue().toString());


        //
        metrics.setJvmThreadsLive(metricsEndpoint.metric("jvm.threads.live",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmThreadsPeak(metricsEndpoint.metric("jvm.threads.peak",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmThreadsStarted(metricsEndpoint.metric("jvm.threads.started",null).getMeasurements().get(0).getValue().toString());
        metrics.setJvmThreadsStates(metricsEndpoint.metric( "jvm.threads.states",null).getMeasurements().get(0).getValue().toString());
        metrics.setLogbackEvents(metricsEndpoint.metric("logback.events",null).getMeasurements().get(0).getValue().toString());
        metrics.setProcessCpuUsage(metricsEndpoint.metric("process.cpu.usage",null).getMeasurements().get(0).getValue().toString());
        metrics.setProcessStartTime(metricsEndpoint.metric("process.start.time",null).getMeasurements().get(0).getValue().toString());
        metrics.setProcessUptime(metricsEndpoint.metric("process.uptime",null).getMeasurements().get(0).getValue().toString());
        metrics.setSystemCpuCount(metricsEndpoint.metric( "system.cpu.count",null).getMeasurements().get(0).getValue().toString());
        metrics.setSystemCpuUsage(metricsEndpoint.metric("system.cpu.usage",null).getMeasurements().get(0).getValue().toString());

        metrics.setTomcatSessionsActiveCurrent(metricsEndpoint.metric("tomcat.sessions.active.current",null).getMeasurements().get(0).getValue().toString());
        metrics.setTomcatSessionsActiveMax(metricsEndpoint.metric("tomcat.sessions.active.max",null).getMeasurements().get(0).getValue().toString());
        metrics.setTomcatSessionsActiveMax(metricsEndpoint.metric("tomcat.sessions.alive.max",null).getMeasurements().get(0).getValue().toString());
        metrics.setTomcatSessionsCreated(metricsEndpoint.metric("tomcat.sessions.created",null).getMeasurements().get(0).getValue().toString());
        metrics.setTomcatSessionsExpired(metricsEndpoint.metric( "tomcat.sessions.expired",null).getMeasurements().get(0).getValue().toString());
        metrics.setTomcatSessionsRejected(metricsEndpoint.metric("tomcat.sessions.rejected",null).getMeasurements().get(0).getValue().toString());


        return metrics;
    }

	@Override
	public ProcessUsageMetricsDto getUsageMetrics() {
		ProcessUsageMetricsDto usageDto = new ProcessUsageMetricsDto();
		usageDto.setName(metricsEndpoint.metric("process.cpu.usage",null).getName());
		usageDto.setDescription(metricsEndpoint.metric("process.cpu.usage",null).getDescription());
		usageDto.setMeasurements(metricsEndpoint.metric("process.cpu.usage",null).getMeasurements().get(0).getValue().toString());
		;
		return usageDto;
	}

	
}

package com.example.actuatorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricsDto {
    private String applicationReadyTime;
    private String applicationStartedTime;
    private String diskTotal;
    private String diskFree;
    private String activeExecutor;
    private String executorPoolCore;
    private String executorCompleted;
    private String executorPoolMax;
    private String executorPoolSize;
    private String executorQueueRemaining;
    private String executorQueued;
    private String httpServerRequests;
    private String  httpServerRequestsActive;
    private String jvmBufferCount;
    private String jvmBufferMemoryUsed;
    private String jvmBufferTotalCapacity;
    private String jvmClassesLoaded;
    private String jvmClassesUnloaded;
    private String jvmCompilationTime;
    private String jvmGcLiveDataSize;
    private String jvmGcMaxDataSize;
    private String jvmGcMemoryAllocated;
    private String jvmGcMemoryPromoted;
    private String jvmGcOverhead;
    private String jvmGcPause;
    private String jvmInfo;
    private String jvmMemoryCommitted;
    private String jvmMemoryMax;
    private String jvmMemoryUsageAfterGc;
    private String jvmMemoryUsed;
    private String jvmThreadsDaemon;
    private String jvmThreadsLive;
    private String jvmThreadsPeak;
    private String jvmThreadsStarted;
    private String jvmThreadsStates;
    private String logbackEvents;
    private String processCpuUsage;
    private String processStartTime;
    private String processUptime;
    private String systemCpuCount;
    private String systemCpuUsage;
    private String tomcatSessionsActiveCurrent;
    private String tomcatSessionsActiveMax;
    private String tomcatSessionsAliveMax;
    private String tomcatSessionsCreated;
    private String tomcatSessionsExpired;
    private String tomcatSessionsRejected;


}

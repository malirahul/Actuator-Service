package com.example.actuatorservice.endpoint;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.util.SystemInfo;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.search.MeterNotFoundException;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

@Component
@Endpoint(id = "memory-alert")
public class MemoryAlertEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(MemoryAlertEndpoint.class);
    
    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private JavaMailSender javaMailSender;
    
    
//    OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
//    long totalMemorySize = osBean.getAvailableProcessors();
//    long usedMemorySize = totalMemorySize - osBean.getFreePhysicalMemorySize();
//    double usedMemoryPercentage = (double)usedMemorySize / totalMemorySize * 100.0;
//    logger.info("Total RAM: " + totalMemorySize + " bytes");
//    System.out.println("Used RAM: " + usedMemorySize + " bytes");
//    System.out.println("Used RAM Percentage: " + usedMemoryPercentage + "%");

    
    
    @ReadOperation
    public String checkMemoryUsage() {
    	
//    	oshi.SystemInfo systemInfo = new oshi.SystemInfo();
//        HardwareAbstractionLayer hardwLayer = systemInfo.getHardware();
//        GlobalMemory totalMemorySize = hardwLayer.getMemory();
//        logger.info("Total Memory : "+totalMemorySize);
        
    	
//    	double totalMemory = (double)totalMemorySize.getTotal();
//    	logger.info("Total memory : "+totalMemory);
//    	
//        double memoryUsage = getMemoryUsage()/1024;
//        logger.info("Memory Used : "+memoryUsage);
//        
//        double freeMemory = (totalMemory - memoryUsage);
        
        double per = (getFreeDisk()/getTotalDisk())*100;
        logger.info("Percentage : "+per);
        
        if (per >= 0) {
            sendEmail(per);
            return "Memory usage is above 90%, Total Memory is "+per+" email sent to user.";
        } else {
            return "Memory usage is below 90%.Free Memory is "+per;
        }
    }

    public double getFreeDisk() {
        try {
            return meterRegistry.find("disk.free").gauge().value();
        } catch (MeterNotFoundException e) {
            logger.info("disk.free gauge not found in registry");
            return -1.0;
        }
    }
    public double getTotalDisk() {
        try {
            return meterRegistry.find("disk.total").gauge().value();
        } catch (MeterNotFoundException e) {
            logger.info("jdisk.total gauge not found in registry");
            return -1.0;
        }
    }

    private void sendEmail(double memoryUsage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("malirahul6425@gmail.com");
        message.setSubject("High Memory usage alert");
        message.setText(String.format("CPU usage is at %.2f%%, please take action to reduce it.", memoryUsage));
        javaMailSender.send(message);
        logger.info("Email sent to user.");
    }
}

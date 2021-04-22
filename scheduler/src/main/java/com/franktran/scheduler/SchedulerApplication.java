package com.franktran.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class SchedulerApplication {

    private Logger logger = LoggerFactory.getLogger(SchedulerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }

//    @Scheduled(fixedRate = 2000) // Max(fixedDelay, method execute duration)
//    @Scheduled(fixedDelay = 2000) // Sum(fixedDelay, method execute duration)
    @Scheduled(cron = "* * * * * *") // seconds minutes hour day month year (https://crontab.guru/)
    public void job() throws InterruptedException {
        logger.info("Current date time of job is " + new Date());
//        Thread.sleep(1000);
    }

    @Scheduled(cron = "* * * * * *")
    public void job2() {
        logger.info("Current date time of job2 is " + new Date());
    }
}

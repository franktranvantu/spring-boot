package com.franktran.logging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    private Logger logger = LoggerFactory.getLogger(HomeService.class);

    public String hello() {
        logger.debug("HomeService");
        return "Hello";
    }
}

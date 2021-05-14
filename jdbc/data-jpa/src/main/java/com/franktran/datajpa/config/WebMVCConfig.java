package com.franktran.datajpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    private final DateRangeFormatter dateRangeFormatter;

    public WebMVCConfig(DateRangeFormatter dateRangeFormatter) {
        this.dateRangeFormatter = dateRangeFormatter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(dateRangeFormatter);
    }
}

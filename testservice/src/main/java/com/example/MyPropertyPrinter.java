package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component

public class MyPropertyPrinter implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(MyPropertyPrinter.class);

	@Value("${foo.bar}")
    private String bar;

    @Value("${foo.buz}")
    private String buz;

    @Value("${foo.joe}")
    private String joe;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("foo.bar {}", bar);
        log.info("foo.buz {}", buz);
        log.info("foo.joe {}", joe);
    }
}

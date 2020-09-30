package com.url.shortner.shorturl.controller.exceptions;

import org.slf4j.LoggerFactory;

public class Handler implements Thread.UncaughtExceptionHandler {
 
    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Handler.class);
 
    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.info("Unhandled exception caught!");
    }
}
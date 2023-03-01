package com.epam.rd.java.basic.topic07.task03.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBException extends Exception {
    private static final Logger log = LogManager.getLogger(PropertiesLoader.class);

    public DBException(Exception e) {
        super(e);
        log.error(e.getMessage() + e.getCause());
    }
}

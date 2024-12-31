package com.designpatterns.creational;

import java.io.Serializable;

/**
 * This class implements a singleton logger with a log() method
 */
public class Logger implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private static volatile Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    /**
     * Logger.log("Message") method from singleton Logger class
     * @param message
     * @see SingletonTest singletonLoggerTest() method with usage example
     */
    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }

    protected Object readResolve() {
        return instance;
    }
}
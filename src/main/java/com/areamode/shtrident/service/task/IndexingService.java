package com.areamode.shtrident.service.task;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class IndexingService<T> implements Runnable {
    public final static String COMMA_DELIMITER = ",";
    public final static String SEPARATOR = "|";

    protected final AtomicInteger counter = new AtomicInteger();

    abstract String getHash(T data);

    abstract boolean exists(T data);

    protected void resetCounter() {
        counter.set(0);
    }

    protected int commit() {
        return counter.addAndGet(1);
    }
}
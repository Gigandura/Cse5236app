package com.example.cse_5236_app;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorRunner {
    private static ExecutorRunner instance;
    public static ExecutorRunner getInstance() {
        if (instance == null) {
            instance = new ExecutorRunner();
        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO() {
        return mNetworkIO;
    }
}

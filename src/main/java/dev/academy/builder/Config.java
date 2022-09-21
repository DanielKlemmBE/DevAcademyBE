package dev.academy.builder;

import java.util.Optional;

public class Config {

    int poolSize;

    String poolName;

    boolean isActive;

    boolean isTerminated;

    boolean isRunning;

    boolean isFast;

    Optional<String> comment;

    Config(int poolSize,
           String poolName,
           boolean isActive,
           boolean isTerminated,
           boolean isRunning,
           boolean isFast,
           Optional<String> comment) {
        this.poolSize = poolSize;
        this.poolName = poolName;
        this.isActive = isActive;
        this.isTerminated = isTerminated;
        this.isRunning = isRunning;
        this.isFast = isFast;
        this.comment = comment;
    }

    public static ConfigBuilder Builder() {
        return ConfigBuilder.Builder();
    }
}

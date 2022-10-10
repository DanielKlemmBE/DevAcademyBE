package dev.academy.patterns.builder;

import java.util.Optional;

public class ConfigBuilder {

    int poolSize;

    String poolName;

    boolean isActive;

    boolean isTerminated;

    boolean isRunning;

    boolean isFast;

    Optional<String> comment = Optional.empty();

    private ConfigBuilder() {
    }

    public static ConfigBuilder Builder() {
        return new ConfigBuilder();
    }

    public ConfigBuilder withPoolSize(int poolSize) {
        this.poolSize = poolSize;
        return this;
    }

    public ConfigBuilder withActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public ConfigBuilder withTerminated(boolean isTerminated) {
        this.isTerminated = isTerminated;
        return this;
    }

    public ConfigBuilder withFast(boolean isFast) {
        this.isFast = isFast;
        return this;
    }

    public ConfigBuilder withRunning(boolean isRunning) {
        this.isRunning = isRunning;
        return this;
    }

    public ConfigBuilder withComment(String comment) {
        this.comment = Optional.ofNullable(comment);
        return this;
    }

    public ConfigBuilder withPoolName(String poolName) {
        this.poolName = poolName;
        return this;
    }

    public Config build() {
        validate();
        return new Config(poolSize,
                poolName,
                isActive,
                isTerminated,
                isRunning,
                isFast,
                comment);
    }

    private void validate() {
        if (poolSize < 2) {
            throw new IllegalArgumentException("Poolsize must be bigger than 2");
        }
    }

}

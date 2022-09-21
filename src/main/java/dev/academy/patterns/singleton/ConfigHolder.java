package dev.academy.patterns.singleton;

public class ConfigHolder {

    private static final ConfigHolder INSTANCE = new ConfigHolder();
    private int poolSize;

    private ConfigHolder() {

    }

    public static ConfigHolder getInstance() {
        return INSTANCE;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
}

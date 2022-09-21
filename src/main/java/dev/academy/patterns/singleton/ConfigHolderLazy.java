package dev.academy.patterns.singleton;

public class ConfigHolderLazy {

    private static ConfigHolderLazy INSTANCE;
    private int poolSize;

    private ConfigHolderLazy() {

    }

    public static ConfigHolderLazy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigHolderLazy();
        }
        return INSTANCE;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
}

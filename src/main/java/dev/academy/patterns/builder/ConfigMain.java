package dev.academy.patterns.builder;

public class ConfigMain {

    public static void main(String[] args) {
        Config config = Config.Builder()
                .withActive(true)
                .withPoolSize(1)
                .withTerminated(false)
                .build();
    }
}

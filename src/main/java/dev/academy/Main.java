package dev.academy;

import dev.academy.builder.Config;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Config config = Config.Builder()
                .withActive(true)
                .withPoolSize(1)
                .withTerminated(false)
                .build();
    }
}
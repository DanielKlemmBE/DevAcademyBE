package dev.academy.patterns.abstractfactory.factory;

public interface Factory<T> {
    boolean assignable(String json);

    T fromJson(String json);
}

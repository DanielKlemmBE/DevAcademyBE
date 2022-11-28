package dev.academy.patterns.abstractfactory.factory;

public interface Factory<T> {
    boolean verify(String json);

    T fromJson(String json);
}

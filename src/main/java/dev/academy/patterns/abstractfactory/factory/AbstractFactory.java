package dev.academy.patterns.abstractfactory.factory;

public abstract class AbstractFactory<T, V> {
    public abstract T get(V value);
}

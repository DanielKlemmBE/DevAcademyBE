package dev.academy.patterns.abstractfactory.model;

import dev.academy.patterns.abstractfactory.factory.AbstractFactory;
import dev.academy.patterns.abstractfactory.factory.Factory;
import dev.academy.patterns.abstractfactory.factory.GroupDataFactory;
import dev.academy.patterns.abstractfactory.factory.UserDataFactory;

import java.util.List;

public class DataObjectFactory extends AbstractFactory<DataObject, String> {
    @Override
    public DataObject get(String json) {
        return factories.stream()
                .filter(factory -> factory.verify(json))
                .findFirst()
                .map(factory -> factory.fromJson(json))
                .orElseThrow(() -> new IllegalArgumentException("can not find factory for Object"));
    }

    private static final List<Factory<? extends DataObject>> factories = List.of(
            new GroupDataFactory(),
            new UserDataFactory());
}

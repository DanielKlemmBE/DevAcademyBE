package dev.academy.patterns.abstractfactory.model;

import dev.academy.patterns.abstractfactory.factory.Factory;
import dev.academy.patterns.abstractfactory.factory.GroupDataFactory;
import dev.academy.patterns.abstractfactory.factory.UserDataFactory;

import java.util.List;
import java.util.Optional;

public class AbstractFactory {

    private static final List<Factory<? extends DataObject>> factories = List.of(GroupDataFactory.instance(), UserDataFactory.instance());

    private static Optional<Factory<? extends DataObject>> findFactory(String json) {
        return factories.stream().filter(factory -> factory.assignable(json)).findFirst();
    }

    /**
     * returns the searched Factory by Enum -> you already know what you are searching for
     */
    public static Factory<? extends DataObject> getFactory(DataObjectTypes type) {
        return switch (type) {
            case USER_DATA -> UserDataFactory.instance();
            case GROUP_DATA -> GroupDataFactory.instance();
        };
    }

    /**
     * convenient method, is using the factory instead of providing it
     */
    public DataObject deserialize(String json) {
        return findFactory(json).map(factory -> factory.fromJson(json)).orElseThrow(() -> new IllegalArgumentException("can not find factory for Object"));
    }

    /**
     * returns the searched Factory by generic 'unknown' String/Json -> you do not know what you are searching for
     */
    public Factory<? extends DataObject> getFactory(String json) {
        return findFactory(json).orElseThrow(() -> new IllegalArgumentException("can not find factory for Object"));
    }
}

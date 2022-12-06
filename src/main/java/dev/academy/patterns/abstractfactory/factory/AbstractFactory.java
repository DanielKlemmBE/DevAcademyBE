package dev.academy.patterns.abstractfactory.factory;

import dev.academy.patterns.abstractfactory.model.DataObject;
import dev.academy.patterns.abstractfactory.model.DataObjectTypes;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class AbstractFactory<T> {
    /**
     * convenient method, is using the factory instead of providing it
     */
    public static DataObject deserialize(String json) {
        return validate(json)
                .flatMap(AbstractFactory::findFactory)
                .map(factory -> factory.fromJson(json))
                .orElseThrow(() -> new IllegalArgumentException("can not find factory for Object"));
    }

    private static Optional<AbstractFactory<? extends DataObject>> findFactory(String json) {
        return Stream.of(
                        GroupDataFactory.instance(),
                        UserDataFactory.instance())
                .filter(factory -> factory.assignable(json))
                .findFirst();
    }

    /**
     * returns the searched Factory by Enum -> you already know what you are searching for
     */
    public static AbstractFactory<? extends DataObject> getFactory(DataObjectTypes type) {
        return switch (type) {
            case USER_DATA -> UserDataFactory.instance();
            case GROUP_DATA -> GroupDataFactory.instance();
        };
    }

    /**
     * returns the searched Factory by generic 'unknown' String/Json -> you do not know what you are searching for
     */
    public static AbstractFactory<? extends DataObject> getFactory(String json) {
        return validate(json)
                .flatMap(AbstractFactory::findFactory)
                .orElseThrow(() -> new IllegalArgumentException("can not find factory for Object"));
    }

    public static Optional<String> validate(String json) {
        // some validation
        return Optional.of(json);
    }

    public abstract boolean assignable(String json);

    public abstract T fromJson(String json);
}

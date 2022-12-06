package dev.academy.patterns.abstractfactory;

import dev.academy.patterns.abstractfactory.factory.AbstractFactory;
import dev.academy.patterns.abstractfactory.model.DataObject;
import dev.academy.patterns.abstractfactory.model.DataObjectTypes;
import dev.academy.patterns.abstractfactory.model.GroupData;
import dev.academy.patterns.abstractfactory.model.UserData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AbstractFactoryUsageTest {

    @Test
    public void deserialize() {
        // setup
        List<DataObject> of = List.of(
                UserData.of("Stefan", 12, "Halle"),
                UserData.of("Daniel", 14, "Halle"),
                GroupData.of("Bearingpoint", 1, "everywhere"),
                GroupData.of("BSI", 2, "some places"));
        List<String> dataAsJson = of.stream().map(DataObject::toJson).toList();


        // to test
        List<DataObject> deserialized = dataAsJson.stream()
                .map(AbstractFactory::deserialize).toList();

        // verify
        assertThat(of).containsExactlyElementsOf(deserialized);
    }

    @Test
    public void deserialize_ObjectType() {
        // to test
        DataObject dataObject1 = AbstractFactory.deserialize(UserData.of("Stefan", 12, "Halle").toJson());
        DataObject dataObject2 = AbstractFactory.deserialize(GroupData.of("Bearingpoint", 1, "everywhere").toJson());

        // verify
        assertThat(dataObject1).isInstanceOf(UserData.class);
        assertThat(dataObject2).isInstanceOf(GroupData.class);
    }

    @Test
    public void deserialize_notFound() {
        assertThatThrownBy(() -> AbstractFactory.deserialize("{\"type\":\"myType\"}"))
                .hasMessage("can not find factory for Object");
    }

    @Test
    public void getFactory_Enum() {
        // to test
        String userString = UserData.of("Stefan", 12, "Halle").toJson();
        AbstractFactory userFactory = AbstractFactory.getFactory(DataObjectTypes.USER_DATA);

        String groupString = GroupData.of("Bearingpoint", 1, "everywhere").toJson();
        // does not make too much sense as we use the groupString twice
        AbstractFactory<? extends DataObject> groupFactory = AbstractFactory.getFactory(groupString);

        // verify
        assertThat(userFactory.fromJson(userString)).isInstanceOf(UserData.class);
        assertThat(groupFactory.fromJson(groupString)).isInstanceOf(GroupData.class);
    }

    @Test
    public void getFactory_list() {
        // setup
        List<DataObject> of = List.of(
                UserData.of("Stefan", 12, "Halle"),
                UserData.of("Daniel", 14, "Halle"),
                GroupData.of("Bearingpoint", 1, "everywhere"),
                GroupData.of("BSI", 2, "some places"));
        List<String> dataAsJson = of.stream().map(DataObject::toJson).toList();


        // to test
        List<DataObject> deserialized = dataAsJson.stream()
                .map(json -> AbstractFactory.getFactory(json).fromJson(json))
                .collect(Collectors.toList());

        // verify
        assertThat(of).containsExactlyElementsOf(deserialized);
    }

    @Test
    public void getFactory_notFound() {
        assertThatThrownBy(() -> AbstractFactory.getFactory("{\"type\":\"myType\"}"))
                .hasMessage("can not find factory for Object");
    }

    @Test
    public void getFactory_single() {
        // to test
        String userString = UserData.of("Stefan", 12, "Halle").toJson();
        AbstractFactory dataObject1 = AbstractFactory.getFactory(userString);

        String groupString = GroupData.of("Bearingpoint", 1, "everywhere").toJson();
        AbstractFactory<? extends DataObject> dataObject2 = AbstractFactory.getFactory(groupString);

        // verify
        assertThat(dataObject1.fromJson(userString)).isInstanceOf(UserData.class);
        assertThat(dataObject2.fromJson(groupString)).isInstanceOf(GroupData.class);
    }
}

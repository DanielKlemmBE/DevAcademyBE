package dev.academy.patterns.abstractfactory;

import dev.academy.patterns.abstractfactory.model.DataObject;
import dev.academy.patterns.abstractfactory.model.DataObjectFactory;
import dev.academy.patterns.abstractfactory.model.GroupData;
import dev.academy.patterns.abstractfactory.model.UserData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FactoryUsageTest {

    DataObjectFactory dataObjectFactory = new DataObjectFactory();

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
                .map(json -> dataObjectFactory.get(json)).toList();

        // verify
        assertThat(of).containsExactlyElementsOf(deserialized);
    }


    @Test
    public void deserialize_ObjectType() {
        // to test
        DataObject dataObject1 = dataObjectFactory.get(UserData.of("Stefan", 12, "Halle").toJson());
        DataObject dataObject2 = dataObjectFactory.get(GroupData.of("Bearingpoint", 1, "everywhere").toJson());

        // verify
        assertThat(dataObject1).isInstanceOf(UserData.class);
        assertThat(dataObject2).isInstanceOf(GroupData.class);
    }

    @Test
    public void deserialize_notFound() {
        assertThatThrownBy(() -> dataObjectFactory.get("{\"type\":\"myType\"}"))
                .hasMessage("can not find factory for Object");

    }
}

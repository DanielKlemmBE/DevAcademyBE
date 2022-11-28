package dev.academy.patterns.abstractfactory.factory;

import dev.academy.patterns.abstractfactory.model.GroupData;
import org.json.JSONObject;

/***
 * This is boilerplate code, most likely this should be generated
 * */
public class GroupDataFactory implements Factory<GroupData> {
    private static final String type = new GroupData().type();

    @Override
    public boolean verify(String json) {
        return new JSONObject(json).getString("type").equals(type);
    }

    @Override
    public GroupData fromJson(String json) {
        JSONObject jo = new JSONObject(json);
        return GroupData.of(jo.getString("name"),
                jo.getInt("nr"),
                jo.getString("position"));
    }
}
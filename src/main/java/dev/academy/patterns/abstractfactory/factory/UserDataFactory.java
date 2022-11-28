package dev.academy.patterns.abstractfactory.factory;

import dev.academy.patterns.abstractfactory.model.UserData;
import org.json.JSONObject;

/***
 * This is boilerplate code, most likely this should be generated
 * */
public class UserDataFactory implements Factory<UserData> {

    private static final String type = new UserData().type();

    @Override
    public boolean verify(String json) {
        return new JSONObject(json).getString("type").equals(type);
    }

    @Override
    public UserData fromJson(String json) {
        JSONObject jo = new JSONObject(json);
        return UserData.of(jo.getString("name"),
                jo.getInt("age"),
                jo.getString("city"));
    }
}

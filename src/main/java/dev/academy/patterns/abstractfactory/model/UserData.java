package dev.academy.patterns.abstractfactory.model;

import org.json.JSONObject;

import java.util.Objects;

public class UserData implements DataObject {

    private String name;
    private int age;
    private String city;

    public static UserData of(String name, int age, String city) {
        UserData userData = new UserData();
        userData.name = name;
        userData.age = age;
        userData.city = city;
        return userData;
    }

    @Override
    public String type() {
        return UserData.class.getName();
    }

    @Override
    public String toJson() {
        JSONObject jo = new JSONObject();
        jo.put("type", type());
        jo.put("name", name);
        jo.put("age", age);
        jo.put("city", city);
        return jo.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return age == userData.age && Objects.equals(name, userData.name) && Objects.equals(city, userData.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, city);
    }
}

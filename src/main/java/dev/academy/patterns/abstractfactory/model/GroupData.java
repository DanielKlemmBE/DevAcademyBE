package dev.academy.patterns.abstractfactory.model;

import org.json.JSONObject;

import java.util.Objects;

public class GroupData implements DataObject {

    private String name;
    private int nr;
    private String position;

    public static GroupData of(String name, int nr, String position) {
        GroupData userData = new GroupData();
        userData.name = name;
        userData.nr = nr;
        userData.position = position;
        return userData;
    }

    @Override
    public String type() {
        return GroupData.class.getName();
    }

    @Override
    public String toJson() {
        JSONObject jo = new JSONObject();
        jo.put("type", type());
        jo.put("name", name);
        jo.put("nr", nr);
        jo.put("position", position);
        return jo.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return nr == groupData.nr && Objects.equals(name, groupData.name) && Objects.equals(position, groupData.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nr, position);
    }
}

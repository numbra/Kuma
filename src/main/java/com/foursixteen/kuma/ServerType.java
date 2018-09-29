package com.foursixteen.kuma;

public enum ServerType {

    SPIGOT("spigot"),
    VANILLA("minecraft"),
    BUKKIT("bukkit");

    String name;

    ServerType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

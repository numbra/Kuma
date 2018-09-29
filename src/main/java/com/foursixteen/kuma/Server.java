package com.foursixteen.kuma;

import java.util.UUID;

public class Server extends Configurable {

    private UUID uuid;
    private ServerType type;

    public Server(UUID uuid) {
        super("servers/" + uuid.toString());
        this.uuid = uuid;
    }

    public Server() {
        this(UUID.randomUUID());
    }

    public void setName(String name) {
        properties.setProperty("displayName", name);
        properties.save(fos, null);
    }
}

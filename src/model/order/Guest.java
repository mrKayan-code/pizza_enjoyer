package model.order;

import java.util.UUID;

import model.common.Identifiable;
import model.common.Named;

public class Guest implements Identifiable, Named{
    private final UUID id = UUID.randomUUID();
    private String name;

    public Guest(String name) {
        this.name = name;
    }


    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Гость %s", name);
    }
}

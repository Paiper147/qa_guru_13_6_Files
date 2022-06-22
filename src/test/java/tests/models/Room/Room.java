package tests.models.Room;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown=true)
public class Room {
    private String room;
    private ArrayList<String> things;
    private Boolean needMoreThings;
    private Integer numberOfMicrowaves;
    private Cutlery cutlery;
    private ArrayList<Shelf> shelves;

    public Room() {
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public ArrayList<String> getThings() {
        return things;
    }

    public void setThings(ArrayList<String> things) {
        this.things = things;
    }

    public Boolean isNeedMoreThings() {
        return needMoreThings;
    }

    public void setNeedMoreThings(Boolean needMoreThings) {
        this.needMoreThings = needMoreThings;
    }

    public Integer getNumberOfMicrowaves() {
        return numberOfMicrowaves;
    }

    public void setNumberOfMicrowaves(Integer numberOfMicrowaves) {
        this.numberOfMicrowaves = numberOfMicrowaves;
    }

    public Cutlery getCutlery() {
        return cutlery;
    }

    public void setCutlery(Cutlery cutlery) {
        this.cutlery = cutlery;
    }

    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(ArrayList<Shelf> shelves) {
        this.shelves = shelves;
    }
}

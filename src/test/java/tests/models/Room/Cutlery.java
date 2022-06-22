package tests.models.Room;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown=true)
public class Cutlery {
    private Integer spoons;
    private Integer forks;
    private Integer knives;

    public Cutlery() {

    }

    public Integer getSpoons() {
        return spoons;
    }

    public void setSpoons(Integer spoons) {
        this.spoons = spoons;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getKnives() {
        return knives;
    }

    public void setKnives(Integer knives) {
        this.knives = knives;
    }
}

package tests.models.Room;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown=true)
public class Shelf {
    private String name;
    private Integer numbersOfСup;

    public Shelf() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumbersOfСup() {
        return numbersOfСup;
    }

    public void setNumbersOfСup(Integer numbersOfСup) {
        this.numbersOfСup = numbersOfСup;
    }
}

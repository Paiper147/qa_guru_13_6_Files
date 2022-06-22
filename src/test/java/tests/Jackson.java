package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.models.Room.Room;

import java.io.InputStream;

public class Jackson {

    ClassLoader classLoader = Jackson.class.getClassLoader();

    @Test
    void fromJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try (InputStream inputStreamFile = classLoader.getResourceAsStream("room-kitchen.json")){
            Room room = mapper.readValue(inputStreamFile, Room.class);

            Assertions.assertThat(room.getRoom()).isEqualTo("Kitchen");
            Assertions.assertThat(room.getThings().get(0)).isEqualTo("Table");
            Assertions.assertThat(room.getThings().get(1)).isEqualTo("Fridge");
            Assertions.assertThat(room.isNeedMoreThings()).isEqualTo(true);
            Assertions.assertThat(room.getNumberOfMicrowaves()).isEqualTo(2);
            Assertions.assertThat(room.getCutlery().getSpoons()).isEqualTo(10);
            Assertions.assertThat(room.getCutlery().getForks()).isEqualTo(15);
            Assertions.assertThat(room.getCutlery().getKnives()).isEqualTo(20);
            Assertions.assertThat(room.getShelves().get(0).getName()).isEqualTo("First");
            Assertions.assertThat(room.getShelves().get(0).getNumbersOfСup()).isEqualTo(3);
            Assertions.assertThat(room.getShelves().get(1).getName()).isEqualTo("Second");
            Assertions.assertThat(room.getShelves().get(1).getNumbersOfСup()).isEqualTo(5);
        }

    }
}

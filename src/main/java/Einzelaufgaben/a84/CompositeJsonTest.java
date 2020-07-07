package Einzelaufgaben.a84;

import Einzelaufgaben.a81.SpecialIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import static org.junit.jupiter.api.Assertions.*;


class CompositeJsonTest {
    SpecialIterator<Integer> iter;

    @BeforeEach
    void SetUp() {

    }


    @Test
    void add() {
        JsonObject json = Json.createObjectBuilder()
                .add("quartalsergebnisse", Json.createObjectBuilder()
                        .add("q1", 10)
                        .add("q2", 10)
                        .add("q3", 5)
                        .add("q4", Json.createArrayBuilder()
                            .add(Json.createObjectBuilder()
                                .add("Dezember", 1)
                                .add("Januar", 3)
                                .add("Februar", 1)
                            )
        )).build();

        System.out.println(json);

        assertSame(30, CompositeJson.addJsonElements(json));


    }
}
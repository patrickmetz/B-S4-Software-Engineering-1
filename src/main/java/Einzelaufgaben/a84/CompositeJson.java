package Einzelaufgaben.a84;

import javax.json.*;

public class CompositeJson {
    public static int addJsonElements(JsonValue json) {
        if (json.getValueType() == JsonValue.ValueType.OBJECT) {
            return ((JsonObject) json)
                    .values()
                    .stream()
                    .mapToInt(CompositeJson::addJsonElements)
                    .sum();
        }

        if (json.getValueType() == JsonValue.ValueType.ARRAY) {
            return ((JsonArray) json)
                    .getValuesAs(JsonObject.class)
                    .stream()
                    .mapToInt(CompositeJson::addJsonElements)
                    .sum();
        }

        if (json.getValueType() != JsonValue.ValueType.NUMBER) {
            throw new RuntimeException("Unexpected type");
        }

        return ((JsonNumber) json).intValue();
    }
}

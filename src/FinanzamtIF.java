import javax.json.JsonObject;

/**
 * @author Johannes Kratzsch
 */
public interface FinanzamtIF {
    void sendTaxReturn(JsonObject json);
}

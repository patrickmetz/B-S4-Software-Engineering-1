import javax.json.JsonObject;

/**
 * @author Johannes Kratzsch
 */
public class Finanzamt implements FinanzamtIF {
    static final Finanzamt finanzamt = new Finanzamt();

    private Finanzamt () {

    }

    public static Finanzamt getInstance() {
        return finanzamt;
    }

    @Override
    public void sendTaxReturn(JsonObject json) {
        System.out.println(json);
    }

    public static float getNetFromGross (float revenue) {
        return revenue - getTaxFromGross(revenue);
    }

    public static float getTaxFromGross (float revenue) {
        float taxRate = 0.19f;

        float baseValue = revenue / (1 + taxRate);
        float salesTax = (revenue - baseValue);

        return salesTax;
    }
}

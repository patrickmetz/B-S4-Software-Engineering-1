import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class KundenDatenUtils {

    public static String formatDauer(int dauer) {
        String dauerString = String.format("%7s", dauer).replace(' ', '0');

        StringBuilder formattedString = new StringBuilder(dauerString);
        formattedString.insert(2, ':');
        formattedString.insert(5, ':');

        return formattedString.toString();
    }

    public static String floatToEuro(float amount) {
        NumberFormat numberFormat =
                NumberFormat.getCurrencyInstance(new Locale("de", "DE"));
        numberFormat.setCurrency(Currency.getInstance("EUR"));

        return numberFormat.format(amount);
    }
}

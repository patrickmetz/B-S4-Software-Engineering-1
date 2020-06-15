package preis;

/**
 * @author Patrick Metz
 */
public final class PreisFactory {
    final static private String klassenPraefix = "preis.Preis";

    public static PreisIF erzeugePreis(String kundenTyp, float preisBetrag) {
        if (preisBetrag < 0) {
            loggeNegativenPreis(kundenTyp, preisBetrag);
            return null;
        }

        PreisIF preis = null;

        try {
            preis = erzeugePreisObjekt(kundenTyp, preisBetrag);
        } catch (Exception e) {
            loggeGescheiterteErzeugung(kundenTyp);
        }

        return preis;
    }

    private static PreisIF erzeugePreisObjekt(String kundenTyp, float preisBetrag) throws Exception {
        Float parameter = preisBetrag;

        return (PreisIF) Class
                .forName(klassenPraefix + kundenTyp)
                .getConstructor(Float.class)
                .newInstance(parameter);
    }

    private static void loggeNegativenPreis(String kundenTyp, float preisBetrag) {
        System.out.println(
                "Negativer Preis '" + preisBetrag + "' " + "für Kundentyp '" + kundenTyp + "' abgelehnt."
        );
    }

    private static void loggeGescheiterteErzeugung(String kundenTyp) {
        System.out.println(
                "Preisklasse '" + klassenPraefix + kundenTyp + "' konnte nicht instanziiert werden."
        );
    }
}

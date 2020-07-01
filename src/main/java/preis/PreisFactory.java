package preis;

import kunde.KundenTypIF;

/**
 * @author Patrick Metz
 */
public final class PreisFactory {
    final static private String klassenPraefix = "preis.Preis";

    public static PreisIF erzeugePreis(KundenTypIF kundenTyp, float preisBetrag) {
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

    private static PreisIF erzeugePreisObjekt(KundenTypIF kundenTyp, float preisBetrag) throws Exception {
        return (PreisIF) Class
                .forName(klassenPraefix + kundenTyp.getTyp())
                .getConstructor(Float.class, String.class)
                .newInstance(preisBetrag, kundenTyp.getBezeichnung());
    }

    private static void loggeNegativenPreis(KundenTypIF kundenTyp, float preisBetrag) {
        System.out.println(
                "Negativer Preis '" + preisBetrag + "' " + "für Kundentyp '" + kundenTyp.getTyp() + "' abgelehnt."
        );
    }

    private static void loggeGescheiterteErzeugung(KundenTypIF kundenTyp) {
        System.out.println(
                "Preisklasse '" + klassenPraefix + kundenTyp.getTyp() + "' konnte nicht instanziiert werden."
        );
    }
}

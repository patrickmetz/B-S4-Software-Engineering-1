package preis;

/**
 * @author Patrick Metz
 */
public final class PreisFactory {

    public static PreisIF erzeugePreis(String preisTyp, float preisBetrag)
            throws ClassNotFoundException, IllegalArgumentException{

        pruefeNegativenBetrag(preisBetrag);

        // according to client categories defined in /web/index.jsp
        return switch (preisTyp) {
            case "Standard"             -> new Preis(preisBetrag);

            case "Frau"                 -> new PreisFrau(preisBetrag);
            case "Familie"              -> new PreisFamilie(preisBetrag);
            case "Mitarbeiter"          -> new PreisMitarbeiter(preisBetrag);
            case "PersonMitBehinderung" -> new PreisBehindert(preisBetrag);

            default                     -> throw new ClassNotFoundException();
        };
    }

    private static void pruefeNegativenBetrag(float preisBetrag) throws IllegalArgumentException {
        if (preisBetrag < 0){
            throw new IllegalArgumentException();
        }
    }

}

package kunde;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Patrick Metz
 *
 * <p>
 * Für die hier hinterlegten Kundentypen, müssen Preisklassen
 * der Form "PreisTYP" existieren; z.B. "PreisMitarbeiter".
 * <p>
 * Außerdem werden sie automatisch an den JavaScript-Parkhaus-
 * Client übergeben.
 * <p>
 * Die PreisObjekte werden initial mit den hier hinterlegten
 * Standardpreisen erstellt. Diese sind jedoch für die Preis-
 * verwaltung überschreibbar.
 *
 * Bezeichnungen dienen nur "schöneren" Texten im HTML-Formular
 */

public enum KundenTyp implements KundenTypIF{
    Familie {
        public float getInitialPreis() {
            return 15.0f;
        }
    },

    Frau {
        public float getInitialPreis() {
            return 9f;
        }
    },

    Mitarbeiter {
        public float getInitialPreis() {
            return 5.0f;
        }
    },

    PersonMitBehinderung {
        public float getInitialPreis() {
            return 7.5f;
        }
    },

    Standard {
        public float getInitialPreis() {
            return 10.0f;
        }
    };

    @Override
    public float getInitialPreis() {
        return 0.0f;
    }

    @Override
    public String getBezeichnung() {
        return super.name();
    }

    @Override
    public String getTyp() {
        return this.toString();
    }

    public static String alsJsonArray() {
        String json = Stream
                .of(KundenTyp.values())
                .map(x -> "\"" + x + "\"")
                .collect(Collectors.joining(","));

        return "[" + json + "]";
    }
}

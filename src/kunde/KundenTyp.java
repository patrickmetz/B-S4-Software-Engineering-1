package kunde;

/**
 * @author Patrick Metz
 * <p>
 * Für die hier hinterlegten Kundentypen, müssen Preisklassen
 * der Form "PreisTYP" existieren; z.B. "PreisMitarbeiter".
 * <p>
 * Außerdem müssen sie den entsprechenden Strings in der JSON-
 * Konfiguration des JavaScript-Parkhause-Clients entsprechen.
 *
 * Die PreisObjekte werden initial mit den hier hinterlegten
 * Standardpreisen erstellt. Diese sind jedoch für die Preis-
 * verwaltung überschreibbar.
 */

public enum KundenTyp {
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

    public float getInitialPreis() {
        return 0.0f;
    }
}

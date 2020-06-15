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
        public float getPreis() {return 15.0f;}
    },

    Frau {
        public float getPreis() {return 9f;}
    },

    Mitarbeiter {
        public float getPreis() {return 5.0f;}
    },

    PersonMitBehinderung {
        public float getPreis() {return 15.0f;}
    },

    Standard {
        public float getPreis() {return 15.0f;}
    };

    public float getPreis() {return 0.0f;}
}

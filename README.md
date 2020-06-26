[[_TOC_]]

# Artefakte
- Digitalisierungskonzept: [s.u.](#digitalisierungskonzept)
- User Stories:
- Kanban-Board: [Link](docs/Kanban Board)
- Priorisierung der User Stories
- eventuell Story Map
- Minimum Viable Product (MVP)
- UML Use Case-Diagramm
- Robustheitsdiagramm
- UML Klassendiagramme: [Link](docs/Design)
- UML Sequenzdiagramme: [Link](docs/Design)
- UML Aktivitätsdiagramme: [Link](docs/Design)
- UML Verteilungsdiagramm : [Link](docs/Design)
- Java - Interfaces: [Link](src)
- lauffähige JUnit-Tests: [Link](test)
- lauffähige Java - Klassen: [Link](src)
- Verzeichnis der eingesetzten Patterns: [s.u.](#verzeichnis-der-eingesetzten-patterns)
- Zielkonflikte: Haben Sie Zielkonflikte erkennen können? Wie haben Sie diese aufgelöst?
- Iterationsbericht: [Link](docs/Kanban Board)
- Summarisches Projektprotokoll:
- Resumée und Fazit: [s.u.](#resumee-und-fazit)
- Präsentation und Demo des Projekts (PPT, PDF, HTML, MP4)

# Digitalisierungskonzept:
Was verstehen Sie unter Digitalisierung? Einfach nur das Analoge digital nachbauen? Oder wird
 auch das Geschäftsmodell / der Mehrwert / die Nutzbarkeit im Digitalen transformiert?

# Resumée und Fazit: Was lief gut? Was hat sich bewährt? Was waren Erfolgsrezepte? Was lief schlecht? Was würde man
 anders machen, wenn man noch einmal neu anfangen könnte? Also was hat man daraus gelernt und was würde man im nächsten Projekt besser machen? (Es geht also um die Selbstevaluation, keine zweite Evaluation der Lehrveranstaltung!)

# Verzeichnis der eingesetzten Patterns
| Design Pattern | Links zum Code | Kommentar |
| ---      |  ------  |-------|
| Command | [TaxReturn](src/TaxReturn.java) | Es handelt sich um das funktionale Command Pattern |
| Singleton | [Finanzamt](src/Finanzamt.java) | |
| MVC / Observer (1) | [Model](src/preis/PreisVerwaltungModel.java) [View](src/preis/PreisVerwaltungView.java) [Controller](src/preis/PreisVerwaltungController.java)   | |
| MVC / Observer (2) | [Model](src/ParkhausStatistics.java) [View](src/JahresEinnahmenView.java) [Controller](src/EinnahmenController.java)   | |


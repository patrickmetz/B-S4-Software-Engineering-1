<!--- Author: Team --->


# Inhaltsverzeichnis
[[_TOC_]]

# Artefakte
- Digitalisierungskonzept: [s.u.](#digitalisierungskonzept)
- User Stories:
- Kanban-Board: [Link](docs/Kanban Board)
- Priorisierung der User Stories
- eventuell Story Map
- Minimum Viable Product (MVP)
- UML Use Case-Diagramm
- Robustheitsdiagramm [Link](docs/Design/Robustheit%20(Aufgabe%205.2).puml)
- UML Klassendiagramme: [Link](docs/Design)
- UML Sequenzdiagramme: [Link](docs/Design)
- UML Aktivitätsdiagramme: [Link](docs/Design)
- UML Verteilungsdiagramm : [Link](docs/Design)
- Java - Interfaces: [Link](src)
- lauffähige JUnit-Tests: [Link](test)
- lauffähige Java - Klassen: [Link](src)
- Verzeichnis der eingesetzten Patterns: [s.u.](#verzeichnis-der-eingesetzten-patterns)
- Zielkonflikte: [s.u.](#zielkonflikte)
- Iterationsbericht: [Link](docs/Kanban Board)
- Summarisches Projektprotokoll:
- Resumée und Fazit: [s.u.](#resumee-und-fazit)
- Präsentation und Demo des Projekts (PPT, PDF, HTML, MP4)

# Digitalisierungskonzept:
Was verstehen Sie unter Digitalisierung? Einfach nur das Analoge digital nachbauen? Oder wird
 auch das Geschäftsmodell / der Mehrwert / die Nutzbarkeit im Digitalen transformiert?

# Zielkonflikte
Haben Sie Zielkonflikte erkennen können? Wie haben Sie diese aufgelöst?

# Resumée und Fazit:
Was lief gut? Was hat sich bewährt? Was waren Erfolgsrezepte? Was lief schlecht? Was würde man
 anders machen, wenn man noch einmal neu anfangen könnte? Also was hat man daraus gelernt und was würde man im nächsten Projekt besser machen? (Es geht also um die Selbstevaluation, keine zweite Evaluation der Lehrveranstaltung!)

# Verzeichnis der eingesetzten Patterns
| Design Pattern | Links zum Code | Kommentar |
| ---      |  ------  |-------|
| Adapter | [GirocardAdapter](src/PaymentProvider/GirocardAdapter.java) | Der [GirocardAdapter](src/PaymentProvider/GirocardAdapter.java) löst die Imkompatibilität des Legacy Zahlungsanbieters [Girocard](src/PaymentProvider/Girocard.java) mit unserer Schnittstelle [GirocardAdapter](src/PaymentProvider/PaymentProviderIF.java) auf. |
| Command | [TaxReturn](src/TaxReturn.java) | Die Generierung der monatlichen Steuerdaten an das Finanzamt ist in einem funktionalen Kommando gekapselt. |
| Composite | [ParkhausChartProcessor](src/ParkhausChartProcessor.java) | Die Json-Struktur wird zu einem Kompositum zusammengebaut. |
| Iterator | [ParkhausServlet](src/ParkhausServlet.java) | Das Iterator-Pattern wird im Rahmen der Enhanced For Loops verwendet, z.B. in Zeile 160 |
| Multiton | [MultitonFahrzeugTyp](src/Fahrzeuge/MultitonFahrzeugTyp.java) | Die Fahrzeugtypen und ihr dazugehörigen Spezifikationen werden einmalig instanziiert. |
| MVC / Observer (1) | [Model](src/preis/PreisVerwaltungModel.java), [View](src/preis/PreisVerwaltungView.java), [Controller](src/preis/PreisVerwaltungController.java)   | Serverseite der Preisverwaltung |
| MVC / Observer (2) | [Model](angular/parkhaus/files/app/preisformular/preis.service.ts),  [View](angular/parkhaus/files/app/preisformular/preisformular.component.html), [Controller](angular/parkhaus/files/app/preisformular/preisformular.component.ts)| Clientseite der Preisverwaltung  |
| MVC / Observer (3) | [Model](src/ParkhausStatistics.java), [View](src/JahresEinnahmenView.java), [Controller](src/EinnahmenController.java)   | ParkhausStatistics |
| Singleton | [Finanzamt](src/Finanzamt.java) | Die Schnittstelle zum Finanzamt kann nur einmalig instanziiert werden. |
| Static Factory Method | [PreisFactory](src/preis/PreisFactory.java) | In unserem Fall sinnvoll und OCP. Nutzt Javas "Class.forName(...).getConstructor(...).newInstance(...)" |
| Template Method | [EinnahmenCalculator](src/Einnahmen/EinnahmenCalculator.java) | Die abstrakte Klasse  [EinnahmenCalculator](src/Einnahmen/EinnahmenCalculator.java) gibt die Struktur für die Berechung der Einnahmen vor. Die Unterklassen wie [JahresEinnahmenCalculator](src/Einnahmen/JahresEinnahmenCalculator.java) und [MonatsEinnahmenCalculator](src/Einnahmen/MonatsEinnahmenCalculator.java) implementieren die Details des Algorithmus. |


<!--- Author: Team --->


# Inhaltsverzeichnis
[[_TOC_]]

# Wichtige Ordner
- [Alle Dokumente](docs)
- [Sourcecode](src/main)
- [Tests](src/test/java)
- [Selbstgeschriebene Tools](tools)

# Artefakte
- Digitalisierungskonzept: [s.u.](#digitalisierungskonzept)
- User Stories:
- Priorisierung der User Stories
- Minimum Viable Product (MVP)
- Kanban-Board: [Link](docs/Kanban Board)
- UML Use Case-Diagramme: [Link](docs/Design/Use%20Cases%20(Aufgabe%204.3).puml)
- Robustheitsdiagramm [Link](docs/Design/Robustheit%20(Aufgabe%205.2).puml)
- UML Klassendiagramme: [Link](docs/Design/Klassendiagramme)
- UML Sequenzdiagramme: [Link](docs/Design/Sequenzdiagramm%20(Aufgabe%205.2).puml)
- UML Aktivitätsdiagramme: [Link](docs/Design/Use%20Cases%20(Aufgabe%204.3).puml)
- Java - Interfaces: [Link](src/main/java)
- lauffähige JUnit-Tests: [Link](src/test/java)
- lauffähige Java - Klassen: [Link](src/main/java)
- Verzeichnis der eingesetzten Patterns: [s.u.](#verzeichnis-der-eingesetzten-patterns)
- Zielkonflikte: [s.u.](#zielkonflikte)
- Iterationsbericht: [Link](docs/Kanban Board)
- Summarisches Projektprotokoll: [Link](docs/Projektmanagement/Summarisches%20Projektprotokoll.pdf)
- Resumée und Fazit: [s.u.](#resumée-und-fazit)
- Präsentation und Demo des Projekts (PPT, PDF, HTML, MP4)

# Digitalisierungskonzept:
**Was verstehen Sie unter Digitalisierung? Einfach nur das Analoge digital nachbauen? Oder wird
 auch das Geschäftsmodell / der Mehrwert / die Nutzbarkeit im Digitalen transformiert?**

**Initiale Produktvision**

 Abläufe von vorhandenem Parkhaus durch Simulation digitalisieren.
 Verschiedene Kundengruppen berücksichtigen.

**Zielgruppe**

 Parkhausbesitzer, Parkhauskunden

**Mehrwert (durch Erkenntnisse aus der Simulation)**

 Kosteneinsparungen durch Reduzierung des Personals
 Reduzierung/Verhinderung von menschlichen Fehlern
 Automatisierung von Routineaufgaben
 Weniger Wartezeit für die Kunden durch Beschleunigung der Abläufe

 -> Aus diesem Digitalisierungskonzept ergab sich dann unser [Anforderungskatalog](docs/Projektmanagement/Anforderungskatalog%20(Aufgabe%204.3).txt)


# Zielkonflikte
Haben Sie Zielkonflikte erkennen können? Wie haben Sie diese aufgelöst?

# Resumée und Fazit:
Was lief gut? Was hat sich bewährt? Was waren Erfolgsrezepte? Was lief schlecht? Was würde man
 anders machen, wenn man noch einmal neu anfangen könnte? Also was hat man daraus gelernt und was würde man im nächsten Projekt besser machen? (Es geht also um die Selbstevaluation, keine zweite Evaluation der Lehrveranstaltung!)

# Verzeichnis der eingesetzten Patterns
| Design Pattern | Links zum Code | Kommentar |
| ---      |  ------  |-------|
| Adapter | [GirocardAdapter](src/main/java/PaymentProvider/GirocardAdapter.java) | Der [GirocardAdapter](src/main/java/PaymentProvider/GirocardAdapter.java) löst die Imkompatibilität des Legacy Zahlungsanbieters [Girocard](src/main/java/PaymentProvider/Girocard.java) mit unserer Schnittstelle [GirocardAdapter](src/main/java/PaymentProvider/PaymentProviderIF.java) auf. |
| Command | [TaxReturn](src/main/java/TaxReturn.java) | Die Generierung der monatlichen Steuerdaten an das Finanzamt ist in einem funktionalen Kommando gekapselt. |
| Composite | [ParkhausChartProcessor](src/main/java/ParkhausChartProcessor.java) | Die Json-Struktur wird zu einem Kompositum zusammengebaut. |
| Iterator | [ParkhausServlet](src/main/java/ParkhausServlet.java) | Das Iterator-Pattern wird im Rahmen der Enhanced For Loops verwendet, z.B. in Zeile 160 |
| Multiton | [MultitonFahrzeugTyp](src/main/java/Fahrzeuge/MultitonFahrzeugTyp.java) | Die Fahrzeugtypen und ihr dazugehörigen Spezifikationen werden einmalig instanziiert. |
| MVC / Observer (1) | [Model](src/main/java/preis/PreisVerwaltungModel.java), [View](src/main/java/preis/PreisVerwaltungView.java), [Controller](src/main/java/preis/PreisVerwaltungController.java)   | Serverseite der Preisverwaltung |
| MVC / Observer (2) | [Model](angular/parkhaus/files/app/preisformular/preis.service.ts),  [View](angular/parkhaus/files/app/preisformular/preisformular.component.html), [Controller](angular/parkhaus/files/app/preisformular/preisformular.component.ts)| Clientseite der Preisverwaltung  |
| MVC / Observer (3) | [Model](src/main/java/ParkhausStatistics.java), [View](src/main/java/JahresEinnahmenView.java), [Controller](src/main/java/EinnahmenController.java)   | ParkhausStatistics |
| Singleton | [Finanzamt](src/main/java/Finanzamt.java) | Die Schnittstelle zum Finanzamt kann nur einmalig instanziiert werden. |
| Static Factory Method | [PreisFactory](src/main/java/preis/PreisFactory.java) | In unserem Fall sinnvoll und OCP. Nutzt Javas "Class.forName(...).getConstructor(...).newInstance(...)" |
| Template Method | [EinnahmenCalculator](src/main/java/Einnahmen/EinnahmenCalculator.java) | Die abstrakte Klasse  [EinnahmenCalculator](src/main/java/Einnahmen/EinnahmenCalculator.java) gibt die Struktur für die Berechung der Einnahmen vor. Die Unterklassen wie [JahresEinnahmenCalculator](src/main/java/Einnahmen/JahresEinnahmenCalculator.java) und [MonatsEinnahmenCalculator](src/main/java/Einnahmen/MonatsEinnahmenCalculator.java) implementieren die Details des Algorithmus. |


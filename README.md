<!--- Author: Team --->


# Inhaltsverzeichnis
[[_TOC_]]

# Wichtige Ordner
- [Alle Dokumente](docs)
- [Sourcecode](src/main)
- [Tests](src/test/java)
- [Selbstgeschriebene Tools](tools)

# CI/CD-Links
- [Unser Projekt bei Jenkins (SEPP)](https://sepp-jenkins.inf.h-brs.de/view/SE1%20SoSe%202020%20(Kaul)/job/Team14/)
- [Unser Projekt bei Sonar Cube (SEPP)](https://sepp-sonar.inf.h-brs.de/dashboard?id=Team14)

# Artefakte
Die Autoren sind jeweils ganz oben in den Dateien genannt. Wenn das nicht ging (zum Beispiel bei Bildern) ist der Autor im Dateinamen genannt.

- Digitalisierungskonzept: [s.u.](#digitalisierungskonzept)
- User Stories: [Link](docs/Projektmanagement/Userstories%20(Autor:%20Team).jpg)
- Priorisierung der User Stories: [Link](docs/Projektmanagement/Priorisierte%20Userstories%20(Autor:%20Team).jpg)
- Minimum Viable Product (MVP): [Link](docs/Projektmanagement/MVP%20(Autor:%20Team).jpg)
- Kanban-Board: [Link](docs/Kanban Board)
- UML Use Case-Diagramme: [Link](docs/Design/Use%20Cases%20(Aufgabe%204.3).puml)
- Robustheitsdiagramm [Link](docs/Design/Robustheit%20(Aufgabe%205.2).puml)
- UML Klassendiagramme: [Link](docs/Design/Klassendiagramme)
- UML Sequenzdiagramme: [Link](docs/Design/Sequenzdiagramm%20(Aufgabe%205.2).puml)
- UML Aktivitätsdiagramme: [Link](docs/Design/Aktivitätsdiagramm%20(Aufgabe%207.1).puml)
- Java - Interfaces: [Link](src/main/java)
- lauffähige JUnit-Tests: [Link](src/test/java)
- lauffähige Java - Klassen: [Link](src/main/java)
- Verzeichnis der eingesetzten Patterns: [s.u.](#verzeichnis-der-eingesetzten-patterns)
- Zielkonflikte: [s.u.](#zielkonflikte)
- Iterationsbericht: [Link](docs/Kanban Board)
- Summarisches Projektprotokoll: [Link](docs/Projektmanagement/Summarisches%20Projektprotokoll.pdf)
- Resumée und Fazit: [s.u.](#resumée-und-fazit)

# Digitalisierungskonzept:
(Autor: Team)

**Was verstehen Sie unter Digitalisierung? Einfach nur das Analoge digital nachbauen? Oder wird
 auch das Geschäftsmodell / der Mehrwert / die Nutzbarkeit im Digitalen transformiert?**

*Initiale Produktvision:* Abläufe von vorhandenem Parkhaus durch Simulation digitalisieren. Dabei soll das analoge Parkhaus nicht einfach 1 zu 1 nachgebaut werden, sondern es soll durch ein tatsächlicher Mehrwert für alle beteiligten entstehen.
 Es sollen verschiedene Kundengruppen (Familien, Personen mit Handicap, Mitarbeiter) berücksichtigt werden.

*Zielgruppe:* Parkhausbesitzer, Parkhauskunden

*Mehrwert (durch Erkenntnisse aus der Simulation)*
- Kosteneinsparungen durch Reduzierung des Personals
- Reduzierung/Verhinderung von menschlichen Fehlern
- Automatisierung von Routineaufgaben
- Weniger Wartezeit für die Kunden durch Beschleunigung der Abläufe

 -> Aus diesem Digitalisierungskonzept ergab sich dann unser [Anforderungskatalog](docs/Projektmanagement/Anforderungskatalog%20(Aufgabe%204.3).txt)


# Zielkonflikte
(Autor: Team)

**Haben Sie Zielkonflikte erkennen können? Wie haben Sie diese aufgelöst?**
- Es war für uns zeitlich nicht möglich, all unsere Ziele umzusetzen. Einerseits hatten wir die Liste Userstories, die wir zu Beginn des Projekts festgelegt hatten Und andererseits hatten wir eine Liste an Tasks, die wir uns im Verlauf des Projekts gesetzt hatten. Schließlich priorisierten wir Tasks wie die CI/CD Pipeline mit SonarQube und Refactoring und setzten dafür zwei unserer Userstories nicht um (den Abschluss von Abonnements und die Ansicht der historische Parkhausbelegung).

# Resumée und Fazit:
(Autor: Team)

**Was lief gut? Was hat sich bewährt? Was waren Erfolgsrezepte?  (Es geht also um die Selbstevaluation, keine zweite Evaluation der Lehrveranstaltung!)**
- Einsatz von Design Patterns: sorgen für besseres Verständnis des Codes und führen zu schnelleren Problemlösungen
- Funktionale Programmierung: sehr viel Logik kann kompakt und trotzdem verständlich gekapselt werden. Die Stream API mit filter-map-reduce gibt hierfür einen einfach zu lernenden Rahmen vor.
- Intuitiveres Verständnis für Zusammenarbeit entwickelt durch das Teamwork in den 10 Wochen
- Zeiten und Vereinbarungen eingehalten


**Was lief schlecht? Was würde man
 anders machen, wenn man noch einmal neu anfangen könnte? Also was hat man daraus gelernt und was würde man im nächsten Projekt besser machen?**
- Nicht alle ursprünglich geplanten Features konnten implementiert werden
    - Abschluss von Abonnements nicht möglich
    - es gibt keine Ansicht der historischen Parkhausbelegung
- Parkhaus-Servlet
    - nicht besonders refaktoriert, weil keine Zeit mehr da war. Daraus haben wir gelernt: für Refactoring sollte mehr Zeit eingeplant werden. Außerdem wäre das Refactoring einfacher gefallen, hätten wir das Servlet gut durch Tests abgedeckt.
    - nicht durch Tests abgedeckt. Nachträglich Tests zu schreiben wäre jetzt sehr zeitaufwändig -> es wäre besser gewesen, die Tests im Rahmen von TDD direkt zu schreiben
- Story und Value Points wurden im späteren Projektverlauf nicht mehr geschätzt. Dadurch war die Priorisierung der Tasks nicht mehr gegeben und es gab weniger Vorhersagbarkeit über den Verlauf des Sprints.


# Verzeichnis der eingesetzten Patterns
(Autor: Team)

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

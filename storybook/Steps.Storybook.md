# Bowling Kata

## Einführung

Wir möchten ein Einblick geben in den Entwickleralltag.
Das Beispiel ist natürlich künstlich, so dass es in den Zeitrahmen passt,
aber das Vorgehen, etc. ist so wie wir normalerweise arbeiten.

Zuerst möchten wir euch ein wenig kennenlernen: Menti-Umfrage - Wer macht was?
Menti --> https://www.mentimeter.com/s/bbec3c8291def01b10fd94e29fb698bf/5de8e46bda0e/edit

## Coding Session

<Ziel der Umsetzung verstehen>

Entwickler 1:
In der nächsten Story im Backlog geht es darum, einen Punkterechner für ein Bowlingspiel zu implementieren. 
Wir sollen die Punkte für die einzelnen Würfe mitgeben können und am Schluss des Spiels den Gesamtpunktstand abfragen können.

Entwickler 2:
Ah, ja. Ich erinnere mich ans Refinement dieser Story.
Das soll irgendwie so aussehen: [stepGoal/Game](src/main/java/com/zuehlke/bowling/stepGoal/Game.java)
Hmm... ich weiss aber gerade nicht mehr genau, wie das mit den Punkten schon wieder ging.

<Requirements abklären>

Entwickler 1:
Bin auch nicht mehr ganz sicher, irgendwas mit Strikes und Spares und Bonuspunkten.
Wir fragen doch noch einmal beim ProductOwner (PO) nach.

Entwickler 2:
Du, PO, kannst Du uns noch einmal die Bowling-Punkteregeln erklären?

PO:
Es ist ganz einfach. Hier die Spezifikation ...
- 10 Frames, jeweils à 2 Rolls
- Punkte: alle Pins werden aufsummiert
- Strike: alle 10 Pins mit eine Roll, Frame ist dann beendet; nächste 2 Rolls sind der Bonus
- Spare: alle 10 Pins mit 2 Rolls; der nächste Roll ist der Bonus

Reicht das ? Alles klar ?

<Unklarheiten beseitigen, Nachfragen>

Entwickler 1 & 2: <verstehen nix von der Spezfikation> 
Kannst du mal ein paar Beispiele machen ?

PO:
Beispiel 1: Alle Würfe 0 --> 0 Punkte
Beispiel 2: Alle Würfe 1 --> 20 Punkte
Beispiel 3: Alle Würfe 10 --> 300 Punkte (=10 * 30)
Beispiel 4: Alle Würfe 5 --> 150 Punkte (=10 * 15)
Beispiel 5: Würfe (5,5) (3,0) und dann 16 x (0,0) --> 16 Punkte (Spare!)

Entwickler 1 & 2:
Alles klar! Wir gehen mal entwickeln.

Entwickler 1:
Ok, was machen wir als nächstes?

Menti: 
Was als nächtes? A) Methode `roll` implementieren? B) Methode `score` implementieren?

<Test-Driven-Development>

Entwickler 2:
Hmm.. ich möchte eigentlich lieber test-driven vorgehen. Das bedeutet ...

<Erklärung von TDD in 1-2 Sätzen>

Entwickler 1:
Ah, das ist eine gute Idee. Dann schreibe ich mal einen ersten Test.
Nehmen wir doch das einfachste Beispiel von oben: 
ML: [step0/GameTest](src/test/java/com/zuehlke/bowling/step0/GameTest.java)

Entwickler 2:
Genau! Dieser Test schläg nun fehl.
Machen wir doch einfach mal eine erste Implementation der Methode:
ML: [step0/Game](src/main/java/com/zuehlke/bowling/step1/Game.java)

<Unklarheiten enstehen während der Entwicklung, nicht alle Requirements bekannt/wohldefiniert>

Entwickler 1:
Ah, jetzt sieht es besser aus.
Aber ist dieser Testfall nicht ein bisschen kompliziert?
Sollten wir nicht einfach mal nach einem Wurf die Punkte abfragen?

Entwickler 2:
Ich bin gerade nicht sicher, was wir überhaupt zurückgeben sollen,
wenn das Spiel noch nicht fertig ist?

Menti: Was sollen wir zurückgeben, wenn das Spiel noch nicht fertig ist?
A) Zwischenstand berechnen B) Exception werfen C)  Egal

Entwickler 1:
Ich glaube, wir fragen noch einmal den PO.

PO:
Diesen Fall müsst ihr nicht behandeln, die Rechner soll immer nur am Ende des Spiels die Gesamtpunktzahl berechnen.

Entwickler 1:
Ok, dann könnten wir ein weiteres Beispiel anschauen. 
Was passiert, wenn wir in allen Würfen genau einen Punkt haben?
ML: [step1/GameTest](src/test/java/com/zuehlke/bowling/step1/GameTest.java)

Entwickler 2:
Ich glaube, wir müssen die Punkte zusammenzählen. Versuchen wir das mal.
ML: [step1/Game](src/main/java/com/zuehlke/bowling/step1/Game.java)
Jetzt sieht es besser aus.

<Code Duplication entsteht schnell, besonders bei Tests>

Entwickler 1:
Bevor wir die weitere Tests schreiben, sollten wir unsere Tests noch ein wenig aufräumen.
Dort hat es noch viel duplizierten Code:
- `Game game` als Feld extrahieren
- `this.game = new Game()` als neue Methode extrahieren, mit `@BeforeEach` annotieren
- `for`-Schleife als Methode `rollMultipleTimes` extrahieren
ML: [step2/GameTest](src/test/java/com/zuehlke/bowling/step2/GameTest.java)

<Design: Responsibility-Driven-Design>

Entwickler 2:
Viel besser, aber Ich bin nicht wirklich zufrieden mit der aktuellen Lösung.
- `roll()` behandelt Würfe und Berechnung in einem
- `score()` macht eigentlich nichts oder nicht was der Name impliziert
Lass uns doch diese Responsibilities richtig verteilen.
ML: [step3/Game](src/main/java/com/zuehlke/bowling/step3/Game.java)

<Quick and Dirty Hacks>

Entwickler 1:
Jetzt ist alles sauber. Bisher kann unser Spiel aber noch nicht besonders viel. 
Versuchen wir als nächstes mal den Strike+Bonus zu implementieren.
Habe schon eine Idee wie wir das machen können. Ich implementiere das kurz mal während du in der Kaffepause bist.
ML: [step4/GameTest](src/test/java/com/zuehlke/bowling/step3/GameTest.java)
Copy/paste der ML [step4/Game](src/main/java/com/zuehlke/bowling/step3/Game.java).

Entwickler 2:
Ist das dein erst? Du jonglierst hier mit Indexen und Listen herum ? Lass uns mal ein sauberes Objekt-Orientiertes Design diskutieren.

<Design Diskussion>

Menti: Für welche Lösung würdet ihr euch entscheiden?

1. Weiterfahren mit Indexen.

2. Frames mit Hilfe `List<List<Int>>` modellieren. Wäre am schnellsten. Haben wir schon fast. Einfach und schnell

3. echtes `Frame` modellieren

Entwickler 1:
Dann lass uns nun die interne Implementation ändern um das neue pragmatische Design (2) umzusetzen. 
- ersetzte `rolls` durch eine Liste von Listen
ML: [step5/Game](src/main/java/com/zuehlke/bowling/step5/Game.java).
ML: [step5/GameTest](src/test/java/com/zuehlke/bowling/step5/GameTest.java).

<ein Feature nach dem anderen>

Entwickler 2:
Alle Tests sind grün. Unser Game kann nun mit normalen Frames und Strikes umgehen. 
Jetzt fehlen uns noch die Spares. Lass uns dazu wieder zuerst einen Test implementieren.
ML: [step6/GameTest](src/test/java/com/zuehlke/bowling/step6/GameTest.java).
Und jetzt noch die Implementation.
ML: [step6/Game](src/main/java/com/zuehlke/bowling/step6/Game.java).

<Test-Qualität, Test-Data Qualität>

Entwickler 1:
Ist dir aufgefallen dass unsere Tests nur Schönwetter-Fälle repräsentieren? 
Die Spielverläufe sind zudem sehr unrealistisch. Lass uns noch ein paar realistische Test-Cases umsetzen.
- implementiere `mixedRolls()` als eine Mischung aus Spares und Strikes
- implementiere `mixedRolls_realGame()` als eine Mischung aus Spares und Strikes
ML: [step6/GameTest](src/test/java/com/zuehlke/bowling/step6/GameTest.java).

<Änderungen an der Architektur mitten im Projekt>

Entwickler 2:
Ich glaube wir hätten uns für das andere Design/Architektur entscheiden sollen. 
Die Interne Representation durch Listen in Listen ist nicht wirklich Objekt-Orientiert. 
Der Code ist nicht lesbar und das Spiel wird nicht gut durch den Code dokumentiert. 
Man braucht viel Insider-Wissen um das zu verstehen.
Lass uns das Umbauen. Es ist noch nicht zu spät.
- implementiere `Frame`. Verwende so viele Refactoring von IntelliJ wie nur möglich
ML: [step7/Game](src/main/java/com/zuehlke/bowling/step7/Game.java).

<Änderungen in der Implementation ziehen auch Änderungen in den Tests nach>

Entwickler 1:
Jetzt sollten wir aber auch die Tests anpassen und diese von rolls auf frames umschreiben.

Entwickler 2:
Sehr gute Idee.
- implementiere `roll(scoreRoll1, scoreRoll2)` als einen methoden-aufruf
- implementiere `strike()` und `spare()` als separaten methoden-aufruf
ML: [step7/GameTest](src/test/java/com/zuehlke/bowling/step7/GameTest.java).
Schau wie gut leserlich unsere Tests sind. Weitere Tests lassen sich nun ganz einfach implementieren.

<Clean Code, Best Practice>

Entwickler 2:
Wir sollten noch weiter gehen und all die Bedingungen und Konstanten in lesbare Form bringen ... bis der Code self-documenting wird
- implementiere utils wie `isStrike()`, `isSpare()`, `isComplete()` 
- ersetzte `rollMultipleTimes()` durch `repeat()`
ML: [step8/Game](src/main/java/com/zuehlke/bowling/step8/Game.java).
ML: [step8/Game](src/test/java/com/zuehlke/bowling/step8/GameTest.java).

Entwickler 2:
Schon viel besser. Aber die Logik zur Berechnung der Punkte ist noch immer auf `Game` und `Frame` verteilt - nicht sauber.
Lass uns das noch verbessern.

<Pragmatische Lösungen / Vergoldung>

PO:
Leute, bitte vergoldet hier nichts. Ich hab das Spiel gerade getestet. Es funktioniert so wie spezifiziert. Wann können wir das shippen ?

Entwickler 1:
Wir vergolden nichts. Wir müssen noch ein paar notwendige Refactorings machen. Wenn wir die jetzt nicht machen, wird die weitere Entwicklung sehr aufwendig und teuer. Besser jetzt als später.
- schiebe die gesamte Punke-Logik nach `Frame`
- implementiere `nextFrame` als State in Frame (weil Punkte-Berechnung hängt von den Folge-Frames ab)
ML: [step9/Game](src/main/java/com/zuehlke/bowling/step8/Game.java).
ML: [step9/Game](src/test/java/com/zuehlke/bowling/step8/GameTest.java).

Entwickler 2:
Jetzt ist alles sauber getrennt. ... Wobei ich hätte da schon noch einige Ideen was wir besser machen könnten.

<Pragmatische Lösungen>

Entwickler 1:
Ja ich auch. Aber ich denke wir haben eine gute/pragmatische Lösung gefunden.


*The End*
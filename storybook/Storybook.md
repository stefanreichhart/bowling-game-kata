# Bowling Kata

Einführung: Wir möchten ein Einblick geben in den Entwickleralltag.
Das Beispiel ist natürlich künstlich, so dass es in den Zeitrahmen passt,
aber das Vorgehen, etc. ist so wie wir normalerweise arbeiten.

Zuerst möchten wir euch ein wenig kennenlernen: Menti-Umfrage - Wer macht was?

Entwickler 1: 
In der nächsten Story im Backlog geht es darum, einen Punkterechner für ein Bowlingspiel zu implementieren. 
Wir sollen die Punkte für die einzelnen Würfe mitgeben können und am Schluss des Spiels den Gesamtpunktstand abfragen können.

Entwickler 2:
Ah, ja. Ich erinnere mich ans Refinement dieser Story.
Das soll irgendwie so aussehen: [v0/Game](src/main/java/com/zuehlke/bowling/v0/Game.java)
Hmm... ich weiss aber gerade nicht mehr genau, wie das mit den Punkten schon wieder ging.

Entwickler 1:
Bin auch nicht mehr ganz sicher, irgendwas mit Strikes und Spares und Bonuspunkten.
Wir fragen doch noch einmal beim Productowner nach.

Entwickler 2:
Du, PO, kannst Du uns noch einmal die Bowling-Punkteregeln erklären?

PO:
*erklärt die Bowling-Punkteregeln*
Wollen wir noch ein paar Beispiele durchgehen?
(jeweils mit Menti im Hintergrund)
Beispiel 1: Alle Würfe 0 --> 0 Punkte
Beispiel 2: Alle Würfe 1 --> 20 Punkte
Beispiel 3: Würfe 5, 5, 3, und dann 17 x 0 --> 16 Punkte (Spare!)
Beispiel 3: Alle Würfe 10 --> xxx Punkte
Beispiel 4: (Beispiel mit Spare)
Beispiel 5: (Beispiel mit Strike)

Alles klar?

Entwickler 1 & 2:
Alles klar! Wir gehen mal entwickeln.

Entwickler 1:
Ok, was machen wir als nächstes?

Menti: Was als nächtes? A) Methode `roll` implementieren? B) Methode `score` implementieren?

Entwickler 2:
Hmm.. ich möchte eigentlich lieber test-driven vorgehen. Das bedeutet

Entwickler 1:
Ah, das ist eine gute Idee. Dann schreibe ich mal einen ersten Test.
Nehmen wir doch das einfachste Beispiel von oben: 
[v0/GameTest](src/test/java/com/zuehlke/bowling/v0/GameTest.java)

Entwickler 2:
Genau! Dieser Test schläg nun fehl.
Machen wir doch einfach mal eine erste Implementation der Methode:
[v1/Game](src/main/java/com/zuehlke/bowling/v1/Game.java)

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
[v1/GameTest](src/test/java/com/zuehlke/bowling/v1/GameTest.java)

Entwickler 2:
Ich glaube, wir müssen die Punkte zusammenzählen. Versuchen wir das mal.
[v2/Game](src/main/java/com/zuehlke/bowling/v2/Game.java)
Jetzt sieht es besser aus.

Entwickler 1:
Bevor wir die weitere Tests schreiben, sollten wir unsere Tests noch ein wenig aufräumen.
Dort hat es noch viel duplizierten Code: [v3/GameTest](src/test/java/com/zuehlke/bowling/v3/GameTest.java)
- `Game game` als Feld extrahieren
- `this.game = new Game()` als neue Methode extrahieren, mit `@BeforeEach` annotieren
- `for`-Schleife als Methode `rollTwentyTimes` extrahieren

Entwickler 2:
Versuchen wir jetzt das nächste Beispiel mit einem Spare: 5, 5, 3 und dann 17 x 0.
Ah, ich glaube, zuerst müssen wir unsere ^rollTwentyTimes` Methode noch allgemeiner machen.
- `rollMultipleTimes` extrahieren
Jetzt können wir diesen Testfall implementieren: [v4/GameTest](src/test/java/com/zuehlke/bowling/v4/GameTest.java)

Entwickler 1:
Ja, diesen Fall haben wir noch nicht korrekt implementiert.
Ich glaube, wir müssen unser Design ein wenig überdenken.

*Design Diskussion*
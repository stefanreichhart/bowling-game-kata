# Bowling Kata

## Einführung

Wir möchten ein Einblick geben in den Entwickleralltag.
Das Beispiel ist natürlich künstlich, so dass es in den Zeitrahmen passt,
aber das Vorgehen, etc. ist so wie wir normalerweise arbeiten.

Zuerst möchten wir euch ein wenig kennenlernen: Menti-Umfrage - Wer macht was?
Menti --> https://www.mentimeter.com/s/bbec3c8291def01b10fd94e29fb698bf/5de8e46bda0e/edit

## Coding Session

Entwickler 1:
In der nächsten Story im Backlog geht es darum, einen Punkterechner für ein Bowlingspiel zu implementieren. 
Wir sollen die Punkte für die einzelnen Würfe mitgeben können und am Schluss des Spiels den Gesamtpunktstand abfragen können.

Entwickler 2:
Ah, ja. Ich erinnere mich ans Refinement dieser Story.
Das soll irgendwie so aussehen: [stepGoal/Game](src/main/java/com/zuehlke/bowling/stepGoal/Game.java)
Hmm... ich weiss aber gerade nicht mehr genau, wie das mit den Punkten schon wieder ging.

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

Entwickler 1 & 2: <verstehen nix von der Spezfikation> 
Kannst du mal ein paar Beispiele machen ?

PO:
Beispiel 1: Alle Würfe 0 --> 0 Punkte
Beispiel 2: Alle Würfe 1 --> 20 Punkte
Beispiel 3: Alle Würfe 10 --> 300 Punkte (=10*30)
Beispiel 4: Alle Würfe 5 --> 150 Punkte (=10*15)
Beispiel 5: Würfe (5,5) (3,0) und dann 16 x (0,0) --> 16 Punkte (Spare!)

Entwickler 1 & 2:
Alles klar! Wir gehen mal entwickeln.

Entwickler 1:
Ok, was machen wir als nächstes?

Menti: 
Was als nächtes? A) Methode `roll` implementieren? B) Methode `score` implementieren?

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

Entwickler 1:
Bevor wir die weitere Tests schreiben, sollten wir unsere Tests noch ein wenig aufräumen.
Dort hat es noch viel duplizierten Code:
- `Game game` als Feld extrahieren
- `this.game = new Game()` als neue Methode extrahieren, mit `@BeforeEach` annotieren
- `for`-Schleife als Methode `rollMultipleTimes` extrahieren
ML: [step2/GameTest](src/test/java/com/zuehlke/bowling/step2/GameTest.java)

Entwickler 2:
Viel besser, aber Ich bin nicht wirklich zufrieden mit der aktuellen Lösung.
- `roll()` behandelt Würfe und Berechnung in einem
- `score()` macht eigentlich nichts oder nicht was der Name impliziert
Lass uns doch diese Responsibilities richtig verteilen.
ML: [step3/Game](src/test/java/com/zuehlke/bowling/step3/Game.java)


Entwickler 1:
Jetzt ist alles sauber. Bisher kann unser Spiel aber noch nicht besonders viel. 
Versuchen wir als nächstes mal den Strike+Bonus zu implementieren.
ML: [step4/GameTest](src/test/java/com/zuehlke/bowling/step3/GameTest.java)
Habe schon eine Idee wie wir das machen können. Ich implementiere das kurz mal während du in der Kaffepause bist.
Copy/paste der ML [step4/Game](src/test/java/com/zuehlke/bowling/step3/Game.java).

Entwickler 2:
Ist das dein Erst? Du jonglierst hier mit Indexen und Listen herum ? Lass uns mal ein sauberes Objekt-Orientiertes Design machen.

<Design Diskussion>
- Weiterfahren mit Indexen. 
- Frames mit Hilfe `List<Int>` modellieren. Wäre am schnellsten. Haben wir schon fast.
- echtes `Frame` modellieren

ML [step5/Game](src/test/java/com/zuehlke/bowling/step5/Game.java).
ML [step6/Game](src/test/java/com/zuehlke/bowling/step6/Game.java).
ML [step7/Game](src/test/java/com/zuehlke/bowling/step7/Game.java).




















# Step init
- create testcase with score()
- empty implementation

# Step 0 > 1
- implement first simple testcases
    - always 0 pins
    - always 1 pin
- implement simple scoring logic
    
# Step 1 > 2
- already some code duplication in testcases
- cleanup tests
    - remove copied for-loops

# Step 2 > 3
- add testcase: all strikes
    - implementation fails / implement strike-scoring

# Step 3 > 4
- add testcase: all spares
    - implementation fails / implement spare-scoring
- notice: all very "artificial" testcases
    - add 2 more testcases (with lots of comments for understanding)
- complete until tests are green

# Step 4 > 5
- notice: very dirty testcases
    - refactor/cleanup testcases
    - strike(), spare(), roll()

# Step 5 > 6
- notice: very dirty internal logic, no encapsulation, behaviour is spread over classes
    - refactor ArrayList<Integer> to class Frame
        - by moving stuff around
        - intellij refacts only
        - no new logic
- notice: testcases are still roll-based, instead of frame-based
    - refactor/cleanup testcases
    - timesRepeat()
    
# Step 6 > 7
- notice: better, but still some behaviour is spread over classes (frames/frame)
    - refactor Frame to linked Frames 
        - logic can now be fully encapulated into Frame
    - refactor internal behaviour of game

# Step 7
- notice: encapsulation OK, responsibilities OK, finished ?
- one possible solution
- everything more would be "Vergoldung"
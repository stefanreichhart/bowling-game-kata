# Bowling Cata Game

## Ablauf
| Min | Inhalt |
| --- | --- |
| 5 | - Zühlke vorstellen <br> - Uns selber vorstellen |
| 2 | - Kurz Menti erklären; wird mehrfach verwendet; wir versuchen interaktiv zu sein <br> - MENTI: wer seid ihr ? was macht ihr ? |
| 2 | - Überblick/Inhalt über die heutige Session |
| **10** | --- |
| 10 | - Projekt vorstellen <br> - Rollen vorstellen <br> - Game erklären <br> - MENTI: Beispiele <br> - MENTI: Quiz |
| **20** | --- |
| 15 | Step 0 - 4: TDD, Clean Code |
| 15 | Step 5 - 8: Requirements, Design, Architecture, Team Work |
| **50** | --- |
| 10 | Abschluss <br> - MENTI: Fragen <br> - MENTI: Feedback |
| **60** | --- |

## Detail-Ablauf
| Step | Inhalt |
| --- | --- |
| Step 0 | Problem: womit anfangen ? <br> - MENTI: score() oder roll() ? <br> - TDD vorstellen <br> - Testcase 1 implementieren |
| Step 1 | Problem: bei abfrage score() ist das Spiel noch nicht fertig <br> - MENTI: was machen wir ? <br> - PO fragen, Requirements klären <br> - Implementation nach TDD <br> - Testcase (leeres spiel) + (alle 0) + (alle 1)  |
| Step 2 | Ugly Test code => viel Duplication <br> - Refactoring der Tests <br> Testcode ist auch wichtig (viel wichtiger sogar -> Requirements/Specs) |
| Step 3 | Responsibility driven design: Wer hat verantwortung für score ? score() oder roll() <br> - Design Diskussion <br> - Design anpassen <br> - Tests bleiben gleich |
| Step 4 | Problem: Strike Berechnung: Wir müssen "Frame" kennen <br> - Strike -> nächste Frame bzw nächste 2 Rolls // Strike + Frame erkennen <br> - MENTI: Design; ANtowrten diskutieren; Pro/Contra <br> - Frame modellieren … möglichst mit copy/paste schaffen / sonst zu lange das dieser Schritt recht gross ist |
| Step 5 | Problem: Spare programmieren: neues Feature <br> - wieder nach TDD |
| Step 6 | Tests sind etwas dumm und unrealistisch; zudem noch roll() basiert <br> - Reale Testcases programmieren <br> - Erwähnen: Testcases sind Dokumentation + Spezifikation ! <br> - Testcases ans Frame anpassen |
| Step 7 | MENTI: sind wir fertig ? <br> - Alternative refactorings diskutieren <br> - Pragmatismus in der IT ansprechen <-> keine Vergoldungen|
| Step 8 | Abschluss: Musterlösung von LinkedList-model zeigen |

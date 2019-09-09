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
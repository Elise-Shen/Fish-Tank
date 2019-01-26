# Markdown

Markdown is a plain-text file format. There are lots of programming tools that use Markdown, and it's useful and
easy to learn. Hash marks (the number sign) indicate headers. Asterisks indicate lists.

# List of code smells

## Code Smell 1: Long Method

### Code Smell Category: Bloaters

### List of classes and line numbers involved:

Seaweed.java, method <draw>, Line 44-65

### Description:

The method contains more than 10 lines.

### Solution:

I will extract the method.
Separate method <draw> into 2 method,
method <draw_odd> contains the code for seaweed segment is odd numbered.
method <draw_even> contains the code for seaweed segment is even numbered.
Therefore, each method contains less than 10 line.

### Explanation
Therefore, each method is less than 10 lines. It will be easier to read and update in the future.

============================================================

## Code Smell 2: Switch Statements

### Code Smell Category: Object Orientation Abusers

### List of classes and line numbers involved:

Fish.java, method <reverseAppearance>, Line 66-75

### Description:
From line 66-75, there's a switch statement with 8 cases.

### Solution:
Separate the cases into different methods.
put the cases ")","(" into method <round_bracket>
put the cases "<",">" into method <angle_brackets>
put the cases "{","}" into method <curly_brackets>
put the cases "[", "]" into method <square brackets>
After all, delete the switch statement
and add method calls for 4 new methods

### Explanation
As a OOP language. java doesn't recommend the use of switch statements.
If switch statements are scattered in different parts in the program,
all the switch statements must be changed if a new condition is added.
By extracting and moving method, the problem can be solved.

============================================================

## Code Smell 3: Dead Code

### Code Smell Category: Dispensables

### List of classes and line numbers involved:
FishTank.java, Line 10, Line 12

### Description:
variable <charWidth>, <charHeight> are not used after being defined

### Solution:
Delete these 2 variables.

### Explanation:
Since the variables are unused, keeping them might cause confuses for other users.

============================================================

## Code Smell 4: Duplicate Code

### Code Smell Category: Dispensables

### List of classes and line numbers involved:
FishTank.java, main method, Line 31 - Line 50

### Description:
This part of code creates objects Fish
Line 31-32 is duplicated with Line 47-48
Line 33-34 is duplicated with Line 49-50
Line 41-42 is duplicated with Line 43-44


### Solution:
Delete the duplicated Line
Delete Line 47-48, 49-50, 43-44. There's no duplication anymore.

### Explanation
The duplicated Code will caused 2 exactly same objects being created.
The one created later will replace the one created earlier.
Therefore, it's no use to create 2 of them.

============================================================

## Code Smell 5: Primitive Obsession

### Code Smell Category: Bloaters

### List of classes and line numbers involved:
Fish.java, Line 14

### Description:
Constants are supposed to be used for coding information.
2 constant variables are supposed to be set for the length and width for the frame.

### Solution:
Set 2 constants in Fish.java,
WIDTH = 640, LENGTH = 480

### Explanation
By using constant variables, codes are more flexible.If the size of the code need to be
changed, the developer only need to change the value of the constant.
It's also easier and more organize for the code.


============================================================

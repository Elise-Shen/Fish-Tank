# Markdown

Markdown is a plain-text file format. There are lots of programming tools that use Markdown, and it's useful and
easy to learn. Hash marks (the number sign) indicate headers. Asterisks indicate lists.

# List of code smells

## Code Smell 1: Long Method

### Code Smell Category: Bloaters

### List of classes and line numbers involved:

Seaweed.java, method <name> is draw, Line 44-65

### Description:

The method contains more than 10 lines.

### Solution:

I will extract the method.

### Explanation

Separate method draw into 2 method,
method <draw_odd> contains the code for seaweed segment is odd numbered.
method <draw_even> contains the code for seaweed segment is even numbered.
Therefore, each method contains less than 10 line.

============================================================

## Code Smell 2: Switch Statements

### Code Smell Category: Object Orientation Abusers

### List of classes and line numbers involved:

Fish.java, method <reverseAppearance>, Line 66-75

### Description:
As a OOP language. java doesn't recommend the use of switch statements.

### Solution:
Separate the cases into different methods.

### Explanation
put the cases ")","(" into method <round_bracket>
put the cases "<",">" into method <angle_brackets>
put the cases "{","}" into method <curly_brackets>
put the cases "[", "]" into method <square brackets>
After all, delete the switch statement
and add method calls for 4 new methods

============================================================

## Code Smell 3: [Write the code smell name]

### Code Smell Category: [Write the code smell category name]

### List of classes and line numbers involved:

* [Write a class and list of line numbers, one class per asterisk, that describe the smell]

### Description:

[In your own words, explain how the description of the code smell applies to this particular code.]

### Solution:

[In your own words, explain how you might solve this code smell:
how would you refactor the code?]

### Explanation

[How does your solution get rid of the code smell? Write your explanation here.]

============================================================

## Code Smell 4: [Write the code smell name]

### Code Smell Category: [Write the code smell category name]

### List of classes and line numbers involved:

* [Write a class and list of line numbers, one class per asterisk, that describe the smell]

### Description:

[In your own words, explain how the description of the code smell applies to this particular code.]

### Solution:

[In your own words, explain how you might solve this code smell:
how would you refactor the code?]

### Explanation

[How does your solution get rid of the code smell? Write your explanation here.]

============================================================

## Code Smell 5: [Write the code smell name]

### Code Smell Category: [Write the code smell category name]

### List of classes and line numbers involved:

* [Write a class and list of line numbers, one class per asterisk, that describe the smell]

### Description:

[In your own words, explain how the description of the code smell applies to this particular code.]

### Solution:

[In your own words, explain how you might solve this code smell:
how would you refactor the code?]

### Explanation

[How does your solution get rid of the code smell? Write your explanation here.]

============================================================

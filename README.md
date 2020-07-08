# Quick Reference
## Running a program
```
$ java -jar one.jar
>
example.one java
```
## Programs
```
// Defines the program
(program Example more code...)
// Main function
(main args more code...)
```
## Printing and input
#### Printing
```
// Print "hi" to the console with a newline
(println "hi")
// Print "hi" to the console without a newline
(print "hi")
```
#### Input
```
// Read user input
(read-input)
```
## Variables and constants
#### Variables
```
// Create a local variable
((int) var var-test 123)
// Create a global variable
((int) global-var global-var-test 234)
```
#### Constants
```
// Create a local constant
((int) let let-test 321)
// Create a global constant
((int) global-let global-let-test 432)
```
#### Accessing variables and constants
```
// Access a variable
($ var-name)
// Access a constant or function argument
(@ const-name)
```
## Types
#### Types
```
// No return value
(void)
// Nothing
(empty)
// String
(str)
// Integer
(int)
// Double
(double)
// Boolean
(bool)
// Closure
(closure)
// Any type
(object)
```
#### Casting
```
// Convert to string
(to-str 123)
// Convert to integer
(to-int "string")
// Convert to double
(to-double 321)
// Convert to boolean
(to-bool 0)
```
## Math
```
// Add
(+ 1 1)
// Subtract
(- 2 1)
// Multiply
(* 3 2)
// Divide
(/ 6 3)
// Square root
(sqrt 64)
// Sine
(sin 3)
// Cosine
(cos 3)
// Tangent
(tan 3)
// Absolute value
(abs -3)
// Random integer
(rand-int 10)
```

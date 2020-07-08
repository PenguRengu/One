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
// Prints "hi" to the console with a newline
(println "hi")
// Prints "hi" to the console without a newline
(print "hi")
```
#### Input
```
// Reads user input
(read-input)
```
## Variables and constants
#### Variables
```
// Creates a local variable
((int) var var-test 123)
// Creates a global variable
((int) global-var global-var-test 234)
```
#### Constants
```
// Creates a local constant
((int) let let-test 321)
// Creates a global constant
((int) global-let global-let-test 432)
```
#### Accessing variables and constants
```
// Accesses a variable
($ var-name)
// Accesses a constant or function argument
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

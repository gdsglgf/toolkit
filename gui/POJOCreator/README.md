## POJO Creator
Auto generate Plain Ordinary Java Object.


## Cofig
```
package.classname
identifier=type
```

## Example
```
com.model.classname
name=String
age=int
```

## Build in types
boolean
char
byte
short
int
long
float
double

Boolean
Character
Byte
Short
Integer
Long
Float
Double

## Current Support(collection without T)
List, ArrayList
Set, HashSet
Map, HashMap
Vector
Stack
Queue

## Future Support nested colletion import recognize
List<List<String>>   -> java.util.List

## issues
- 2017-01-17 support nested colletion import recognize
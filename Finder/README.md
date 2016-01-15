#Finder

## Introduction
### Searching
Searching whatever you want.

### Export
Export the searching result as plain text file.

## Installation

```
cd src

javac -encoding utf-8 com/tool/finder/Main.java

java com.tool.finder.Main

jar cvf finder.jar com/tool/finder/*.class

temp.mf
Main-Class: com.tool.finder.Main
Sealed: true

jar -uvmf temp.mf finder.jar
```
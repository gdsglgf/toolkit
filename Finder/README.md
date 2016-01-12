#Finder

cd src

javac com/tool/finder/Main.java

java com.tool.finder.Main

jar cvf finder.jar com/tool/finder/*.class

temp.mf
Main-Class: com.tool.finder.Main
Sealed: true

jar -uvmf temp.mf finder.jar
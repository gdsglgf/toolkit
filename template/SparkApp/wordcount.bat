@echo off

rem mvn clean package

D:/Applications/spark-2.0.2-bin-hadoop2.7/bin/spark-submit ^
  --class com.test.WordCount ^
  --master local[4] ^
  target/test-1.0.jar data/data.txt
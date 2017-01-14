@echo on

rem mvn clean package

D:/Applications/spark-2.0.2-bin-hadoop2.7/bin/spark-submit ^
  --class com.test.SimpleApp ^
  --master local[4] ^
  target/test-1.0.jar data.txt
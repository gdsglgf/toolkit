# WebSpy
Mining text from http url.

# About parser
WebSpy supports 3 parsers, REParser, TAGParser and KVParser.

```
1. REParser
It must start with "RE=".
eg. "RE=(<a.*?>)"

2. TAGParser
It must start with "TAG=".
eg. "TAG=textarea" --> (for csdn urls)

3. KVParser
It is like that "key=value", and "=" is needed.
eg. "name=code"  --> (for iteye urls)
    "class=cnblogs_code"  --> (for cnblogs urls)
```

# Maven export runnable jar
mvn clean compile assembly:single


# Other Jsoup toolkits

## 解析网页链接信息(a标签中href, text属性)
使用命令行参数
mvn exec:java -Dexec.mainClass="com.html.LinkParser" -Dexec.args="http://www.test.com"

## 获取网页标题
使用控制台重定向按行读取文件
mvn exec:java -Dexec.mainClass="com.html.TitleParser" < links.txt


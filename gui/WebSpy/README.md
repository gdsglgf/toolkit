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
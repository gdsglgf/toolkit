## Maven Tool

### three ways to get user home directory
```
home = os.environ['HOME']
os.path.expandvars('$HOME')
os.path.expanduser('~')
```

maven_parser.py
- list all jar files information: groupId, artifactId, version(s) in maven home
```
$python maven_parser.py
```

maven_cleaner.py
- clean the maven home
```
# remove the .lastUpdated file
$python maven_cleaner.py

# remove the dir that has not .jar file
$python maven_cleaner.py T
```
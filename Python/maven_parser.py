'''
maven 本地jar包版本分析工具
    - 列出所有本地已下载的jar包
    - 输出格式如下:
    - groupId, artifactId, version(s)
'''

import os

# 获取当前用户的主目录的三种方式
#home = os.environ['HOME']
#os.path.expandvars('$HOME')
#os.path.expanduser('~')

def parseDependency(line):
    words = line.split('\\')
    groupId = words[0]
    for i in range(1 , len(words) - 2):
        groupId = groupId + '.' + words[i]
    artifactId = words[-2]
    version = words[-1]
    return [groupId, artifactId, version]

maven_home = os.environ['HOME'] + '/.m2/repository/'

length = len(maven_home)
[groupId, artifactId, version] = ['', '', '']
print('groupId', 'artifactId', 'version(s)', end=' ')

for dirpath, dirnames, filenames in os.walk(maven_home):
    jar = []
    for filename in filenames:
        if filename.endswith('.jar'):
            jar.append(filename)
    if (len(jar) > 0):
        # display all jar file path
        # print(dirpath[length:], end=' ')
        # for name in jar:
        #     print(name, end=' ')
        # print()

        # p = dirpath[length:]
        # for name in jar:
        #     print(p + '\\' + name)

        [a, b, c] = parseDependency(dirpath[length:])
        if artifactId != b:
            print()
            print(a, b, c, end=' ')
        elif version != c:
            print(c, end=' ')
        [groupId, artifactId, version] = [a, b, c]

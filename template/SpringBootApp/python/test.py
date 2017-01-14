import json
try:
	from urllib2 import Request, urlopen  # Python 2
	from urllib import urlretrieve
except:
	from urllib.request import Request, urlopen, urlretrieve  # Python 3

url = 'http://localhost:8080/'
content = urlopen(url).read()
print(content)

data = json.load(urlopen('http://localhost:8080/json'))
print(data)
import os
import re
import sys

def show(data):
	for i in data:
		print(i)

	print(data)

def listfiles(path, fileType):
	result = []
	for fpath, dirs, fs in os.walk(path):
		# print(fpath, dirs, fs)
		for f in fs:
			if f.endswith(fileType):
				result.append(os.path.join(fpath, f))

	show(result)

	return result

def findall(filePath, pattern):
	with open(filePath) as fs:
		text = fs.read()
		result = pattern.findall(text)

		show(result)

	return result

def main():
	path = sys.argv[1]
	fileType = sys.argv[2]
	pattern = re.compile(sys.argv[3], re.IGNORECASE)

	for f in listfiles(path, fileType):
		findall(f, pattern)

# Demo: list all python api
# python ../Python py '(def .*:)'
if __name__ == '__main__':
	main()
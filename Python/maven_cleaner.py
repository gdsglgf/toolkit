import os
import sys
from threading import Thread
try:
	from queue import Queue    # python 3
	from urllib.request import urlretrieve
except:
	from Queue import Queue    # python 2
	from urllib import urlretrieve

home = os.path.expanduser("~")
maven_home = os.path.join(home, '.m2/repository')

print('MAVEN HOME: %s' %maven_home)

def listfiles(path, filter=None):
	if os.path.isfile(path) or not os.path.isdir(path):
		return []
	files = [os.path.join(path, f) for f in os.listdir(path)]
	if filter:
		files = [f for f in files if filter(f)]
	return files

def enqueue(dir_queue, files):
	for f in files:
		dir_queue.put(f)

def isEnd(files):
	for f in files:
		if os.path.isdir(f):
			return False
	return True

def check(path, files, rmdir=False):
	if os.path.isfile(path):
		return
	if rmdir:
		jars = listfiles(path, filter=lambda f: f.endswith('.jar'))
		if len(jars) == 0:
			print('-------------------%s' %path)
			for f in files:
				print(f)
				os.remove(f)
			os.rmdir(path)
	else:
		caches = listfiles(path, filter=lambda f: f.endswith('.lastUpdated'))
		for f in caches:
			print(f)
			os.remove(f)

def clean(num_worker_threads=5, rmdir=False):
	dir_queue = Queue()
	files = listfiles(maven_home)
	enqueue(dir_queue, files)

	def worker():
		while not dir_queue.empty():
			path = dir_queue.get()
			files = listfiles(path)
			if isEnd(files):
				check(path, files, rmdir)
			else:
				enqueue(dir_queue, files)
			dir_queue.task_done()

	for i in range(num_worker_threads): # start threads
		worker_thread = Thread(target=worker)
		worker_thread.daemon = True
		worker_thread.start()
	dir_queue.join() # block until all tasks are done
	print
	print('clean done...')

def download(url, path):
	urlretrieve(url, path)

def test_listfiles():
	print(listfiles('maven_cleaner.py'))
	print(listfiles('.'))
	print([f for f in os.listdir() if f.endswith('.txt')])

def test_main():
	test_listfiles()

def main():
	if len(sys.argv) == 2 and sys.argv[1] == 'T':
		clean(rmdir=True)
	else:
		clean()

if __name__ == '__main__':
	# test_main()
	main()
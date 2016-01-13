#! /usr/bin/python

'''
Utilities of file & directories.
- recursive function
- nonrecursive function
- list all local maven compiled jar file
'''

import os

# Get the all files & directories in the specified directory (path).
def get_recursive_file_list(path):
    current_files = os.listdir(path)
    all_files = []
    for file_name in current_files:
        full_file_name = os.path.join(path, file_name)
        all_files.append(full_file_name)

        if os.path.isdir(full_file_name):
            next_level_files = get_recursive_file_list(full_file_name)
            all_files.extend(next_level_files)

    return all_files

def listdir(path):
    files = os.listdir(path)
    for i in range(0, len(files)):
        files[i] = os.path.join(path, files[i])

    return files

def get_nonrecursive_file_list(path):
    all_files = listdir(path)
    index = 0
    while index < len(all_files):
        file_name = all_files[index]
        index = index + 1

        if os.path.isdir(file_name):
            all_files.extend(listdir(file_name))

    return all_files

# print(os.environ)

maven_home = os.environ['HOME'] + '/.m2/repository/'
length = len(maven_home)

all_files = get_nonrecursive_file_list(maven_home)
for i in all_files:
    if (i.endswith('.jar') and (i.endswith('sources.jar') == False)):
        print(i[length:])
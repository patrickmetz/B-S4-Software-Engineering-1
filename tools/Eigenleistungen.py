import os
import re

# Author: Johannes Kratzsch

author = re.compile(r'@author (.*)')

def printFiles(dir):
    for root, dirs, files in os.walk(dir):
        for file in files:
            if file.endswith(".java"):
                path = os.path.join(root, file)
                content = open(path).read()
                matches = author.findall(content)
                names = ""

                if matches is not None:
                    names = ','.join(str(match) for match in matches)

                print(path + ";" + names)


printFiles("../src")
printFiles("../test")

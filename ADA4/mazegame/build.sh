#!/bin/bash

rm -rf out
javac -Xlint:deprecation -d "out/production/mazegame" $(find src -name "*.java")
cp -r resources/* out/production/mazegame/
java -Dfile.encoding=UTF-8 -classpath out/production/mazegame src.view.GameGui

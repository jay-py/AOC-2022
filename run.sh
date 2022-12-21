#!/bin/bash
kotlinc $PWD -include-runtime -d App.jar
java -jar App.jar

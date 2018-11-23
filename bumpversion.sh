#!/bin/sh

sed -i 's/todolist-.*-jar/todolist-'"$1"'-jar/' todolist
mvn versions:set -DnewVersion="$1"

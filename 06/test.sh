#!/bin/bash

if [ "$#" -ge 2 ]; then
    cd groovy-assembler
    ./assembler.groovy ../my-$1/$2.asm
    diff ../my-$1/$2.hack ../$1/$2.hack
else
    echo "usage: test <folder name without my-> <file name without .asm>"
fi

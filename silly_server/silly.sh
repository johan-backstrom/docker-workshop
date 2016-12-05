#! /bin/bash

COUNTER=1
while [ true ]; do
  cowsay "Rocking with Docker!!! ${COUNTER}"
  let COUNTER=COUNTER+1
  sleep 5
done

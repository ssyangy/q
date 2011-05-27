#!/bin/bash
for i in `seq 100000`; do
   random=`shuf -i 1000-9999 -n 1` 
   echo "insert into authcode (id, value) values ($i , '$random' );"  >> initAuthcode.sql
done

#!/bin/bash

raml2html iataaa.raml > ../index.html

if [ $? -eq 0 ]; then
  nohup xdg-open ../index.html >/dev/null 2>&1
fi
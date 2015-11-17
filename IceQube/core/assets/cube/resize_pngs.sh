#!/bin/bash

if [ -z "$2" ]
then
	echo "Missing parameter"
	echo
	echo "$0 [width] [height]"
else
	echo
	echo "Resizing pngs to $1 by $2"
	for image in *.png
	do
		convert $image -resize "$1x$2" $image
	done
	echo "That shit is done yo... check it check it"
	echo
fi

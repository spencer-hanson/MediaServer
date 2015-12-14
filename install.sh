#!/bin/bash
if [ -z "$1" ]; then
echo "Must specify a location to install the media server"
echo "Usage: $0 <install folder>"
exit
fi

mkdir -v $1"/Documents"
mkdir -v $1"/Games"
mkdir -v $1"/Movies"
mkdir -v $1"/Music"
mkdir -v $1"/Pictures"
mkdir -v $1"/Other"

cd $1"/Documents"
cat > extensions.txt << "msg"
txt
doc
docx
md
rtf
ppt
pptx
odf
odt
log
msg

cd ..
cd $1"/Games"
cat > extensions.txt << "z"
rar
7z
zip
iso
tar
gz
z

cd ..
cd $1"/Movies"
cat > extensions.txt << "f4v"
webm
mkv
flv
ogv
gifv
mov
qt
wmv
mp4
mpeg
mpv
mp2
m2v
mpeg2
m4v
3gp
3g2
f4v

cd ..
cd $1"/Music"
cat > extensions.txt << "flac"
m4a
mp3
oog
ogg
oga
wav
wma
flac

cd ..
cd $1"/Pictures"
cat > extensions.txt << "bmp"
png
jpg
jpeg
gif
tiff
tif
bmp

echo Done!

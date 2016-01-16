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
mkdir -v $1"/exts"

cd $1"/exts"
cat > docs.txt << "EOF"
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
EOF

cat > games.txt << "EOF"
rar
7z
zip
iso
tar
gz
EOF


cat > movies.txt << "EOF"
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
EOF


cat > music.txt << "EOF"
m4a
mp3
oog
ogg
oga
wav
wma
flac
EOF


cat > pictures.txt << "EOF"
png
jpg
jpeg
gif
tiff
tif
bmp
EOF

echo Done!

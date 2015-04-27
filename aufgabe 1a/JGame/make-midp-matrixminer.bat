del /S /Q classes-examples-tmp >devnull
mkdir classes-examples-tmp
del /S /Q classes-examples >devnull
mkdir classes-examples

echo "Using WTK_HOME=%WTK_HOME%"

set PREVERIFY=%WTK_HOME%/bin/preverify
set CLDCAPI=%WTK_HOME%/lib/cldcapi11.jar
set MIDPAPI=%WTK_HOME%/lib/midpapi20.jar

set APPNAME=MatrixMinerMidlet
set MANIFESTNAME=manifests\manifest_midp_matrixminer

javac -bootclasspath %CLDCAPI%;%MIDPAPI% -source 1.3 -target 1.3 -classpath classes-midp examples/matrixminer/MatrixMiner.java examples/StdMaze*.java -d classes-examples-tmp

%PREVERIFY% -classpath %CLDCAPI%;%MIDPAPI%;classes-midp -d classes-examples  classes-examples-tmp


copy examples\matrixminer\matrix_miner.tbl classes-examples\examples\matrixminer\
copy examples\matrixminer\matrixminer-splash.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\train16-purple.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\killertomato16.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\dragon16.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\wasp16.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\goodie-circle-tr.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\myexplob16-tr.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\ballbullet-tr.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\font234-red-tr.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\iceblock-16.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\iceblock-purple-yellow-16.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\iceblock-red-16.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\iceblock-orange-16.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\iceblock-yellow-16.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\iceblock-green-16.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\iceblock-cyan-16.gif classes-examples\examples\matrixminer\
copy examples\matrixminer\*.wav classes-examples\examples\matrixminer\

echo "Jaring preverified class files..."
jar cmf %MANIFESTNAME% %APPNAME%.jar -C classes-examples examples -C classes-midp jgame

echo "Please update MIDlet-Jar-Size in jad manually..."

echo MIDlet-Jar-Size: >a.tmp
dir %APPNAME%.jar >b.tmp
type %MANIFESTNAME% a.tmp b.tmp >%APPNAME%.jad


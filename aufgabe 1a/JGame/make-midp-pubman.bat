del /S /Q classes-examples-tmp >devnull
mkdir classes-examples-tmp
del /S /Q classes-examples >devnull
mkdir classes-examples

echo "Using WTK_HOME=%WTK_HOME%"

set PREVERIFY=%WTK_HOME%/bin/preverify
set CLDCAPI=%WTK_HOME%/lib/cldcapi11.jar
set MIDPAPI=%WTK_HOME%/lib/midpapi20.jar

set APPNAME=PubManMidlet
set MANIFESTNAME=manifests\manifest_midp_pubman

javac -bootclasspath %CLDCAPI%;%MIDPAPI% -source 1.3 -target 1.3 -classpath classes-midp examples/PubMan.java examples/StdScoring.java examples/StdMaze*.java -d classes-examples-tmp

%PREVERIFY% -classpath %CLDCAPI%;%MIDPAPI%;classes-midp -d classes-examples  classes-examples-tmp


mkdir classes-examples\examples\gfx
copy examples\pub_man.tbl classes-examples\examples\
copy examples\gfx\pubman-splash.gif classes-examples\examples\gfx\
copy examples\gfx\rollingball-tr.gif classes-examples\examples\gfx\
copy examples\gfx\walkingbeer-tr.gif classes-examples\examples\gfx\
copy examples\gfx\matrixwall.gif classes-examples\examples\gfx\
copy examples\gfx\walkingbeer-blue.gif classes-examples\examples\gfx\
copy examples\gfx\walkingbeer-black.gif classes-examples\examples\gfx\
copy examples\gfx\walkingbeer-white.gif classes-examples\examples\gfx\
copy examples\gfx\goodie-circle-tr.gif classes-examples\examples\gfx\
copy examples\gfx\pacpills.gif classes-examples\examples\gfx\
copy examples\gfx\font234-red-tr.gif classes-examples\examples\gfx\
copy examples\gfx\revolvingdoor.gif classes-examples\examples\gfx\

echo "Jaring preverified class files..."
jar cmf %MANIFESTNAME% %APPNAME%.jar -C classes-examples examples -C classes-midp jgame

echo "Please update MIDlet-Jar-Size in jad manually..."

echo MIDlet-Jar-Size: >a.tmp
dir %APPNAME%.jar >b.tmp
type %MANIFESTNAME% a.tmp b.tmp >%APPNAME%.jad


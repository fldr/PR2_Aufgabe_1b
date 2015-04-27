del /S /Q classes-examples-tmp >devnull
mkdir classes-examples-tmp
del /S /Q classes-examples >devnull
mkdir classes-examples

echo "Using WTK_HOME=%WTK_HOME%"

set PREVERIFY=%WTK_HOME%/bin/preverify
set CLDCAPI=%WTK_HOME%/lib/cldcapi11.jar
set MIDPAPI=%WTK_HOME%/lib/midpapi20.jar

set APPNAME=MunchiesMidlet
set MANIFESTNAME=manifests\manifest_midp_munchies

javac -bootclasspath %CLDCAPI%;%MIDPAPI% -source 1.3 -target 1.3 -classpath classes-midp examples/Munchies.java -d classes-examples-tmp

%PREVERIFY% -classpath %CLDCAPI%;%MIDPAPI%;classes-midp -d classes-examples  classes-examples-tmp



mkdir classes-examples\examples\gfx
copy examples\munchies.tbl classes-examples\examples\
copy examples\gfx\munchie-g.gif classes-examples\examples\gfx\
copy examples\gfx\munchie-y.gif classes-examples\examples\gfx\
copy examples\gfx\munchie-r.gif classes-examples\examples\gfx\
copy examples\gfx\spider16.gif classes-examples\examples\gfx\
copy examples\gfx\scissors-open.gif classes-examples\examples\gfx\
copy examples\gfx\scissors-closed.gif classes-examples\examples\gfx\
copy examples\sound\misc145-eatapple.wav classes-examples\examples\sound\
copy examples\sound\prel5e_c-bach.mid classes-examples\examples\sound\
copy examples\sound\scissors.wav classes-examples\examples\sound\

echo "Jaring preverified class files..."
jar cmf %MANIFESTNAME% %APPNAME%.jar -C classes-examples examples -C classes-midp jgame

echo "Please update MIDlet-Jar-Size in jad manually..."

echo MIDlet-Jar-Size: >a.tmp
dir %APPNAME%.jar >b.tmp
type %MANIFESTNAME% a.tmp b.tmp >%APPNAME%.jad

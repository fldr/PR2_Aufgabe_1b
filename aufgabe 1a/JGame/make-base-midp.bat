del /S /Q classes-midp-tmp >devnull
mkdir classes-midp-tmp
del /S /Q classes-midp >devnull
mkdir classes-midp


echo "Making MIDP classes..."

echo "Using WTK_HOME=%WTK_HOME%"

set PREVERIFY=%WTK_HOME%/bin/preverify
set CLDCAPI=%WTK_HOME%/lib/cldcapi11.jar
set MIDPAPI=%WTK_HOME%/lib/midpapi20.jar

javac -bootclasspath %CLDCAPI%;%MIDPAPI% -source 1.3 -target 1.3 -classpath classes-midp-tmp src-base/jgame/*.java src-base/jgame/impl/*.java src-midp/jgame/platform/*.java -d classes-midp-tmp

%PREVERIFY% -classpath %CLDCAPI%;%MIDPAPI%;classes-midp-tmp -d classes-midp  classes-midp-tmp



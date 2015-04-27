echo "parameter 1: jar filename without .jar extension --> %1.jar"
echo "parameter 2: manifest filename                   --> %2"
echo "Writing to classes-midp-shrunk"
echo ""
echo "  Using WTK_HOME=%WTK_HOME%"
echo "  Using PROGUARD_HOME=%PROGUARD_HOME%"
echo "  Assuming proguard 3.9 or higher command line options"

set PREVERIFY=%WTK_HOME%/bin/preverify
set CLDCAPI=%WTK_HOME%/lib/cldcapi11.jar
set MIDPAPI=%WTK_HOME%/lib/midpapi20.jar
set PROGUARD=%PROGUARD_HOME%\lib\proguard.jar

mkdir classes-midp-shrunk-tmp
mkdir classes-midp-shrunk

echo "Shrinking....."

java -jar %PROGUARD% -dontusemixedcaseclassnames -libraryjars %CLDCAPI% -libraryjars %MIDPAPI% -allowaccessmodification -overloadaggressively -defaultpackage '' -verbose -keep "public class * extends javax.microedition.midlet.MIDlet" -injars %1.jar -outjars classes-midp-shrunk-tmp\%1.jar

echo "Preverifying....."

%PREVERIFY% -classpath %CLDCAPI%;%MIDPAPI%;classes-midp-tmp -d classes-midp-shrunk classes-midp-shrunk-tmp\%1.jar

echo "Please update MIDlet-Jar-Size in jad manually..."

echo MIDlet-Jar-Size: >a.tmp
dir classes-midp-shrunk\%1.jar >b.tmp
type %2 a.tmp b.tmp >classes-midp-shrunk\%1.jad



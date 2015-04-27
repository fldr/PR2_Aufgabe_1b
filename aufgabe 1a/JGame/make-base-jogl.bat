echo "Making JRE/JOGL classes and jar..."

del /S /Q classes-jogl >devnull
mkdir classes-jogl

rem compile jre classes

javac -source 1.4 -target 1.4 -classpath %CLASSPATH%;classes-jogl src-base/jgame/impl/*.java src-base/jgame/*.java src-jre/jgame/platform/*.java -d classes-jogl

rem replace classes with any classes found in jogl platform

javac -source 1.4 -target 1.4 -classpath %CLASSPATH%;classes-jogl src-jogl/jgame/platform/*.java -d classes-jogl

jar cf jgame-jogl.jar -C classes-jogl .


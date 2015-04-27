del /S /Q classes-jre >devnull
mkdir classes-jre


echo "Making JRE classes and jar..."

javac -source 1.3 -target 1.3 -classpath classes-jre src-base/jgame/impl/*.java src-base/jgame/*.java src-jre/jgame/platform/*.java -d classes-jre

jar cf jgame-jre.jar -C classes-jre .



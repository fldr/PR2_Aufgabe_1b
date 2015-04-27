del /S /Q classes-examples >devnull
mkdir classes-examples

xcopy /S classes-jre classes-examples >devnull
javac -source 1.3 -target 1.3 -classpath classes-examples gamegen/*.java tutorial/*.java examples/*.java examples/nebulaalpha/*.java examples/waterworld/*.java examples/matrixminer/*.java examples/packetstorm/*.java examples/guardian/*.java examples/ogrotron/*.java examples/cavernsoffire/*.java examples/dingbats/*.java -d classes-examples

mkdir classes-examples\html
mkdir classes-examples\html\grabs
copy html\grabs\*.* classes-examples\html\grabs >devnull
copy html\*.txt classes-examples\html >devnull
xcopy /S /I examples\gfx classes-examples\examples\gfx >devnull
xcopy /S /I examples\sound classes-examples\examples\sound >devnull
copy examples\*.tbl classes-examples\examples >devnull
copy examples\nebulaalpha\*.* classes-examples\examples\nebulaalpha >devnull
copy examples\waterworld\*.* classes-examples\examples\waterworld >devnull
copy examples\matrixminer\*.* classes-examples\examples\matrixminer >devnull
copy examples\packetstorm\*.* classes-examples\examples\packetstorm >devnull
copy examples\guardian\*.* classes-examples\examples\guardian >devnull
copy examples\ogrotron\*.* classes-examples\examples\ogrotron >devnull
copy examples\dingbats\*.* classes-examples\examples\dingbats >devnull
copy gamegen\*.gif classes-examples\gamegen >devnull
copy gamegen\simplegeneratedgame.tbl classes-examples\gamegen >devnull
copy tutorial\*.gif classes-examples\tutorial >devnull
copy tutorial\*.png classes-examples\tutorial >devnull
copy tutorial\*.tbl classes-examples\tutorial >devnull
copy tutorial\*.wav classes-examples\tutorial >devnull

cd classes-examples
jar cfm ../jgame-all.jar ../manifests/manifest .
jar cf ../smallgames.jar jgame examples/*.class examples/*.tbl examples/gfx examples/sound examples/ogrotron/*.class examples/ogrotron/*.tbl examples/ogrotron/ogrosprites.gif
jar cfm ../nebulaalpha.jar ../manifests/manifest_nebulaalpha examples/nebulaalpha jgame
jar cfm ../waterworld.jar ../manifests/manifest_waterworld examples/waterworld jgame
jar cfm ../matrixminer.jar ../manifests/manifest_matrixminer examples/matrixminer jgame examples/StdMaze*.class
jar cfm ../packetstorm.jar ../manifests/manifest_packetstorm examples/packetstorm jgame
jar cfm ../guardian.jar ../manifests/manifest_guardian examples/guardian examples/StdScoring.class jgame
jar cf ../tutorial.jar jgame tutorial
jar cfm ../jgame-gamegen.jar ../manifests/manifest_gamegen jgame examples/Std*.class gamegen/*.class gamegen/blocks*.gif gamegen/simplegeneratedgame.tbl

cd ..

rem ogrotron and caverns of fire jars not made yet!

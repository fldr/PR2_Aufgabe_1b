#!/bin/sh

# generate the HTML for the example games

./gen-jgame-html.pl examples.Insecticide '' 'archive="../smallgames.jar">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.nebulaalpha.NebulaAlpha '' \
	'archive="../nebulaalpha.jar">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.matrixminer.MatrixMiner '' \
	'archive="../matrixminer.jar">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.PubMan '' 'archive="../smallgames.jar">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.Munchies '' 'archive="../smallgames.jar">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.SpaceRun '' 'archive="../smallgames.jar">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.SpaceRunII '' 'archive="../smallgames.jar">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.SpaceRunIII '' 'archive="../smallgames.jar">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.waterworld.WaterWorld '' \
	'archive="../waterworld.jar">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.ChainReaction '' 'archive="../smallgames.jar">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.DungeonsOfHack '' 'archive="../smallgames.jar">' \
	600x400 1020x680

./gen-jgame-html.pl examples.DungeonsOfHack '-scroll' \
	'archive="../smallgames.jar"> <param name="scrolling" value="yes">' \
	600x400 1020x680

./gen-jgame-html.pl examples.cavernsoffire.CavernsOfFire '' \
	'archive="../CavernsOfFire.jar">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.cavernsoffire.CavernsOfFire '-scroll' \
	'archive="../CavernsOfFire.jar"> <param name="scrolling" value="yes">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.Ramjet '' 'archive="../smallgames.jar">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.packetstorm.PacketStorm '' \
	'archive="../packetstorm.jar">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.guardian.Guardian '' \
	'archive="../guardian.jar">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.ogrotron.Ogrotron '' \
	'archive="../Ogrotron.jar">' \
	320x240 640x480 800x600 1024x768



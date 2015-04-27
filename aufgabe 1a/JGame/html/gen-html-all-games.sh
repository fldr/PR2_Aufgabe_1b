#!/bin/sh

# generate the HTML for the example games

./gen-jgame-html.pl examples.Insecticide '' 'codebase="..">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.nebulaalpha.NebulaAlpha '' 'codebase="..">'. \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.matrixminer.MatrixMiner '' 'codebase="..">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.PubMan '' 'codebase="..">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.Munchies '' 'codebase="..">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.SpaceRun '' 'codebase="..">'. \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.SpaceRunII '' 'codebase="..">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.SpaceRunIII '' 'codebase="..">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.waterworld.WaterWorld '' 'codebase="..">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.ChainReaction '' 'codebase="..">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.DungeonsOfHack '' 'codebase="..">' \
	600x400 1020x680

./gen-jgame-html.pl examples.DungeonsOfHack '-scroll' \
	'codebase=".."> <param name="scrolling" value="yes">' \
	600x400 1020x680

./gen-jgame-html.pl examples.cavernsoffire.CavernsOfFire '' 'codebase="..">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.cavernsoffire.CavernsOfFire '-scroll' \
	'codebase=".."> <param name="scrolling" value="yes">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.Ramjet '' 'codebase="..">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.packetstorm.PacketStorm '' 'codebase="..">' \
	320x240 640x480 800x600 1024x768

./gen-jgame-html.pl examples.guardian.Guardian '' 'codebase="..">' \
	640x480 800x600 1024x768

./gen-jgame-html.pl examples.ogrotron.Ogrotron '' 'codebase="..">' \
	320x240 640x480 800x600 1024x768



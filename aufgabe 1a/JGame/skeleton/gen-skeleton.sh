#!/bin/sh
#

if test $# -eq 3 ;
then
	APPPATH=$1
	PACKAGENAME=$2
	MAINCLASS=$3

	mkdir -p ${APPPATH}

	cat MyGame.java | sed s/MyGame/${MAINCLASS}/ \
		| sed -e "s/package mygame/package ${PACKAGENAME}/" \
		> ${APPPATH}/${MAINCLASS}.java

	cp -i *.png *.tbl build.properties ${APPPATH}
	cp -i manifest_midp_mygame ${APPPATH}/manifest_midp

	cp -Ri res/ ${APPPATH}/
	sed -i -e "s/MyGame/${MAINCLASS}/g" ${APPPATH}/res/layout/main.xml
	sed -i -e "s/MyGame/${MAINCLASS}/g" ${APPPATH}/res/values/strings.xml

	echo 'Main-class: '${PACKAGENAME}.${MAINCLASS} >${APPPATH}/manifest_jre

	cat make-midp-mygame-preamble               >${APPPATH}/make-midp
	echo JARNAME=${MAINCLASS}Midlet            >>${APPPATH}/make-midp
	echo MANIFESTNAME=${APPPATH}/manifest_midp >>${APPPATH}/make-midp
	echo APPPATH=${APPPATH}                    >>${APPPATH}/make-midp
	echo MAINCLASS=${PACKAGENAME}.${MAINCLASS} >>${APPPATH}/make-midp
	echo APPNAME=${MAINCLASS}                  >>${APPPATH}/make-midp
	cat make-midp-mygame-postamble             >>${APPPATH}/make-midp

	cat make-jre-mygame-preamble                >${APPPATH}/make-jre
	echo JARNAME=${MAINCLASS}                  >>${APPPATH}/make-jre
	echo MANIFESTNAME=${APPPATH}/manifest_jre  >>${APPPATH}/make-jre
	echo APPPATH=${APPPATH}                    >>${APPPATH}/make-jre
	echo MAINCLASS=${PACKAGENAME}.${MAINCLASS} >>${APPPATH}/make-jre
	cat make-jre-mygame-postamble              >>${APPPATH}/make-jre

	cat make-android-mygame-preamble            >${APPPATH}/make-android
	echo JARNAME=${MAINCLASS}                  >>${APPPATH}/make-android
	echo APPPATH=${APPPATH}                    >>${APPPATH}/make-android
	echo MAINCLASS=${PACKAGENAME}.${MAINCLASS} >>${APPPATH}/make-android
	echo DESTDIR=classes-android               >>${APPPATH}/make-android
	cat make-android-mygame-postamble          >>${APPPATH}/make-android

	cat AndroidManifest.xml.preamble          >${APPPATH}/AndroidManifest.xml
	echo \ \ \ \ \ \ \ \ android:name=\"${PACKAGENAME}.${MAINCLASS}\" >>${APPPATH}/AndroidManifest.xml
	cat AndroidManifest.xml.postamble        >>${APPPATH}/AndroidManifest.xml

	sed -i -e "s/mygame.MyGame/${PACKAGENAME}/" ${APPPATH}/AndroidManifest.xml


else
	echo "Generate skeleton for jgame app in current directory."
	echo "Supply the following parameters:"
	echo "    path to app directory (i.e. examples/mygame)"
	echo "    package name (i.e. examples.mygame)"
	echo "    main class (i.e. MyGame)"
fi

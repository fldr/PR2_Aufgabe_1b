
# ensure ./*~ are deleted
# ensure examples/*~ and examples/*/*~ are deleted
# ensure tutorial/*~ are deleted
# ensure any Thumbs.db are deleted
# ensure any .*.sw[po] are deleted

# update version in author_message!
# update version in html and launcher

cd ..
tar -c -v --no-recursion -f jgame-$1.tar \
        JGame/README JGame/MANUAL JGame/LICENSE \
        JGame/CHANGES JGame/makepkg.sh JGame/doshrinkjar* \
		JGame/manifests/* JGame/make-* \
		JGame/*.keystore \
		JGame/*.jar JGame/*.jad JGame/*.jnlp JGame/*.apk \
		JGame/src-*/*/*.java JGame/src-*/*/*/*.java \
		JGame/src-*/*/*.html JGame/src-*/*/*/*.html \
        JGame/examples/*.java JGame/examples/*.tbl \
        JGame/examples/package.html \
        JGame/examples/gfx/* JGame/examples/sound/* \
        JGame/examples/nebulaalpha/* \
        JGame/examples/waterworld/* \
        JGame/examples/matrixminer/* \
        JGame/examples/packetstorm/* \
        JGame/examples/guardian/* \
        JGame/examples/ogrotron/* \
        JGame/examples/cavernsoffire/* \
        JGame/examples/dingbats/* \
		JGame/tutorial/* \
		JGame/gamegen/*.java JGame/gamegen/*.class JGame/gamegen/*.tbl \
		JGame/gamegen/README JGame/gamegen/examples/*.appconfig \
		JGame/gamegen/*.gif \
		JGame/gamegen/website/*.html JGame/gamegen/website/*.php \
        JGame/html/*.html JGame/html/*-desc.txt JGame/html/gen-jgame-html.pl \
        JGame/html/gen-html-*.sh \
        JGame/html/grabs/*
tar -r -v -f jgame-$1.tar \
		JGame/classes-midp \
		JGame/classes-jre \
		JGame/src-android \
        JGame/examples/ogrotron/res/ \
        JGame/examples/cavernsoffire/res/ \
		JGame/javadoc \
		JGame/skeleton


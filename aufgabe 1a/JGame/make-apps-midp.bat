rem XXX caverns of fire and ogrotron do not have dos batch file
make-midp-spacerun
make-midp-pubman
make-midp-munchies
rem examples\cavernsoffire\make-midp
make-midp-dungeonsofhack
make-midp-matrixminer
rem examples\ogrotron\make-midp

rem doshrinkjar CavernsOfFireMidlet examples\cavernsoffire\manifest_midp
doshrinkjar DungeonsOfHackMidlet manifests\manifest_midp_dungeonsofhack
doshrinkjar MatrixMinerMidlet manifests\manifest_midp_matrixminer
doshrinkjar MunchiesMidlet manifests\manifest_midp_munchies
doshrinkjar OgrotronMidlet  manifests\manifest_midp_ogrotron
doshrinkjar PubManMidlet manifests\manifest_midp_pubman
doshrinkjar SpaceRunMidlet manifests\manifest_midp_spacerun


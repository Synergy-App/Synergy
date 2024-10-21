package com.sungkyul.synergy.utils

import android.os.Build

// Standard: GALAXY_NOTE9_EMU

const val GALAXY_NOTE9 = "SM-N960N"
const val GALAXY_ON7_FRIME = "SM-G611L"
const val GALAXY_NOTE9_EMU = "Android SDK built for x86"
const val GALAXY_A10E = "?"
const val GALAXY_A31 = "??"

class Models {
    companion object {
        fun tunePos(galaxyNote9:Float=0.0f, galaxyOn7Frime:Float=0.0f, otherwise:Float=0.0f): Float {
            return when(Build.MODEL) {
                GALAXY_NOTE9 -> galaxyNote9
                GALAXY_ON7_FRIME -> galaxyOn7Frime
                else -> otherwise
            }
        }
    }
}

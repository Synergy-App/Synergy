package com.sungkyul.synergy.utils

import android.os.Build

/*
애뮬 기준
AVD Name Galaxy Note9
Galaxy Note9 6.4 1440x2960 xxhdpi
Q Android 10.0 x86
Startup orientation Portrait
Graphics Automatic

 */

class AdaptiveUtils {
    companion object {
        fun dialogTitleMedium(): Float {
            return when (Build.MODEL) {
                GALAXY_NOTE9_EMU -> 26.0f
                GALAXY_NOTE9 -> 26.0f
                GALAXY_A10E -> 30.0f
                GALAXY_A31 -> 30.0f
                else -> 30.0f
            }
        }

        fun dialogContentMedium(): Float {
            return when (Build.MODEL) {
                GALAXY_NOTE9_EMU -> 22.0f
                GALAXY_NOTE9 -> 22.0f
                GALAXY_A10E -> 26.0f
                GALAXY_A31 -> 26.0f
                else -> 26.0f
            }
        }

        fun ratio(x: Float): Float {
            return when (Build.MODEL) {
                GALAXY_NOTE9_EMU -> {
                    x
                }

                GALAXY_NOTE9 -> {
                    x
                }

                GALAXY_A10E -> {
                    x
                }

                GALAXY_A31 -> {
                    x
                }

                else -> {
                    x
                }
            }
        }
    }
}

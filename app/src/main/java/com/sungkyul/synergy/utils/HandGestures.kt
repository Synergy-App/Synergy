package com.sungkyul.synergy.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView

class HandGestures {
    companion object {
        /*
        ## Gesture를 만들어보자!
        ```
        fun createNameGesture(imageView: ImageView): AnimatorSet {
            val animatorSet = AnimatorSet().apply {
                playSequentially()
            }
            return animatorSet
        }
        ```
        */

        // Tap Gesture
        fun createTapGesture(imageView: ImageView): AnimatorSet {
            val fromValue = 1.0f
            val toValue = 1.2f
            val duration = 1000L

            val set1 = AnimatorSet().apply {
                playTogether(
                    ObjectAnimator.ofFloat(imageView, "scaleX", fromValue, toValue),
                    ObjectAnimator.ofFloat(imageView, "scaleY", fromValue, toValue)
                )
            }
            val set2 = AnimatorSet().apply {
                playTogether(
                    ObjectAnimator.ofFloat(imageView, "scaleX", toValue, fromValue),
                    ObjectAnimator.ofFloat(imageView, "scaleY", toValue, fromValue)
                )
            }

            val animatorSet = AnimatorSet().apply {
                this.duration = duration
                interpolator = AccelerateDecelerateInterpolator()
                playSequentially(
                    set1,
                    set2
                )
            }

            return animatorSet
        }

        // Press and Drag Gesture
        fun createPressAndDragGesture(imageView: ImageView): AnimatorSet {
            val fromValueX = 0.0f
            val toValueX = 100.0f
            val fromValueY = 0.0f
            val toValueY = 100.0f
            val duration = 1000L

            val set1 = AnimatorSet().apply {
                playTogether(
                    ObjectAnimator.ofFloat(imageView, "translationX", fromValueX, toValueX),
                    ObjectAnimator.ofFloat(imageView, "translationY", fromValueY, toValueY)
                )
            }

            val animatorSet = AnimatorSet().apply {
                this.duration = duration
                interpolator = AccelerateDecelerateInterpolator()
                playSequentially(
                    set1
                )
            }

            return animatorSet
        }
    }
}

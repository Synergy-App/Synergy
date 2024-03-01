package com.sungkyul.synergy.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView

class HandAnims {
    companion object {
        // 손으로 터치를 안내하는 애니메이션 세트
        fun touchGuideAnimatorSet(imageView: ImageView): AnimatorSet {
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

        // 손으로 터치 후 드래그를 안내하는 애니메이션 세트
        fun touchAndDragGuideAnimatorSet(imageView: ImageView): AnimatorSet {
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

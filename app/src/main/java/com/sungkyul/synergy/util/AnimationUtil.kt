package com.sungkyul.synergy.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation

class AnimationUtil {
    companion object {
        // (duration)ms 동안 drawable의 알파 값이 startAlpha에서 endAlpha로 바뀌는 함수
        // startAlpha, endAlpha의 범위는 [0, 255]이다.
        fun startAlphaAnimation(
            drawable: Drawable,
            duration: Long,
            startAlpha: Int,
            endAlpha: Int
        ) {
            val alphaAnimator = ObjectAnimator.ofInt(
                drawable,
                "alpha",
                startAlpha,
                endAlpha
            )
            alphaAnimator.duration = duration
            alphaAnimator.start()
        }

        // (duration×2)ms 동안 drawable의 알파 값이 startAlpha → endAlpha → startAlpha로 바뀌는 함수
        // startAlpha, endAlpha의 범위는 [0, 255]이다.
        fun startAlphaAnimation2(
            drawable: Drawable,
            duration: Long,
            startAlpha: Int,
            endAlpha: Int
        ) {
            val animator1 = ObjectAnimator.ofInt(
                drawable,
                "alpha",
                startAlpha,
                endAlpha
            )
            val animator2 = ObjectAnimator.ofInt(
                drawable,
                "alpha",
                endAlpha,
                startAlpha
            )

            animator1.duration = duration
            animator2.duration = duration

            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(animator1, animator2)
            animatorSet.start()
        }

        // (duration)ms 동안 view의 스케일이 startScale에서 endScale로 바뀌는 함수
        // startScale, endScale의 범위는 [0.0f, 1.0f]이다.
        fun startScaleAnimation(
            view: View,
            duration: Long,
            startScale: Float,
            endScale: Float
        ) {
            // Ease-Out
            val interpolator = DecelerateInterpolator()

            val scaleAnimation = ScaleAnimation(
                startScale, endScale,
                startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f, // 중심점 X
                Animation.RELATIVE_TO_SELF, 0.5f // 중심점 Y
            )
            scaleAnimation.duration = duration
            scaleAnimation.interpolator = interpolator
            view.startAnimation(scaleAnimation)
        }
    }
}

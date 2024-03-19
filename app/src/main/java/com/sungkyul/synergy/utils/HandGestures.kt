package com.sungkyul.synergy.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.WindowManager
import android.view.WindowMetrics
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView

class HandGestures {
    companion object {
        // Tap Gesture를 생성한다.
        private fun createTapGesture(
            context: Context,
            imageView: ImageView,
            fromValue: Float,
            toValue: Float,
            duration: Long
        ): AnimatorSet {
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

        // Press and Drag Gesture를 생성한다.
        private fun createDragGesture(
            context: Context,
            imageView: ImageView,
            fromValueXDp: Float,
            fromValueYDp: Float,
            toValueXDp: Float,
            toValueYDp: Float,
            movingDuration: Long,
            waitingDuration: Long
        ): AnimatorSet {
            // dp -> px
            val fromValueX = AnimUtils.dpToPx(context, fromValueXDp)
            val fromValueY = AnimUtils.dpToPx(context, fromValueYDp)
            val toValueX = AnimUtils.dpToPx(context, toValueXDp)
            val toValueY = AnimUtils.dpToPx(context, toValueYDp)

            val set1 = AnimatorSet().apply {
                duration = movingDuration
                interpolator = AccelerateDecelerateInterpolator()
                playTogether(
                    ObjectAnimator.ofFloat(imageView, "translationX", fromValueX, toValueX),
                    ObjectAnimator.ofFloat(imageView, "translationY", fromValueY, toValueY)
                )
            }
            val set2 = ValueAnimator.ofInt(0, 1).apply {
                duration = waitingDuration
            }

            val animatorSet = AnimatorSet().apply {
                playSequentially(
                    set1,
                    set2
                )
            }

            return animatorSet
        }


        /*
        ## Gesture를 만들어보자!
        ```
        fun nameGesture(imageView: ImageView): AnimatorSet {
            val animatorSet = AnimatorSet().apply {
                playSequentially()
            }
            return animatorSet
        }
        ```
        */

        fun tapGesture(context: Context, imageView: ImageView): AnimatorSet {
            return createTapGesture(context, imageView, 1.0f, 1.2f, 1000)
        }

        // 설정 스크롤 다운
        fun settingsScrollDownGesture(context: Context, imageView: ImageView): AnimatorSet {
            return createDragGesture(context, imageView, 200.0f, 650.0f, 200.0f, 400.0f, 1500, 500)
        }

        // 디스플레이 밝기 조절
        fun displayLightDragGesture(context: Context, imageView: ImageView): AnimatorSet {
            return createDragGesture(context, imageView, 220.0f, 510.0f, 360.0f, 510.0f, 1500, 500)
        }

        // 디스플레이 스크롤 다운
        fun displayScrollGesture(context: Context, imageView: ImageView): AnimatorSet {
            return createDragGesture(context, imageView, 200.0f, 650.0f, 200.0f, 400.0f, 1500, 500)
        }

        // 텍스트 크기 조절
        fun textSizeDragGesture(context: Context, imageView: ImageView): AnimatorSet {
            return createDragGesture(context, imageView, 40.0f, 660.0f, 335.0f, 660.0f, 2000, 500)
        }
    }
}

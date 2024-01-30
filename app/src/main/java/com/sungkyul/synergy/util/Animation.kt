package com.sungkyul.synergy.util

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View

class Animation {
    companion object {
        /*
            뷰 배경의 알파 값이 터치 여부에 따라 바뀌는 함수.

            [설명]
            뷰를 누르면, 배경의 알파 값이 (duration)ms 동안 touchUpAlpha에서 touchDownAlpha로 바뀐다.
            뷰를 떼면, 배경의 알파 값이 (duration)ms 동안 touchDownAlpha에서 touchUpAlpha로 바뀐다.

            [파라미터]
            - view: 뷰
            - initAlpha: 초기 알파 값 (값은 0 ~ 255 사이)
            - touchUpAlpha: 뷰를 떼고 있을 때의 알파 값 (값은 0 ~ 255 사이)
            - touchDownAlpha: 뷰를 누르고 있을 때의 알파 값 (값은 0 ~ 255 사이)
            - duration: 애니메이션 진행 시간 (ms)

            [예시]
            // button을 누르면 버튼 배경이 서서히 나타나고, 떼면 서서히 사라진다.
            Animation.setViewBgAlphaAnimationOnTouch(button, 0, 0, 255, 100)
        */
        fun setViewBgAlphaAnimationOnTouch(
            view: View,
            initAlpha: Int,
            touchUpAlpha: Int,
            touchDownAlpha: Int,
            duration: Long
        ) {
            view.background.alpha = initAlpha

            view.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startAlphaAnimation(v.background, duration, touchUpAlpha, touchDownAlpha)
                    }

                    MotionEvent.ACTION_UP -> {
                        startAlphaAnimation(v.background, duration, touchDownAlpha, touchUpAlpha)
                    }
                }
                true
            }
        }

        // 배경의 알파 값을 바꾸는 애니메이션 함수.
        // (duration)ms 동안 알파 값이 startAlpha에서 endAlpha로 바뀐다.
        private fun startAlphaAnimation(
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
    }
}

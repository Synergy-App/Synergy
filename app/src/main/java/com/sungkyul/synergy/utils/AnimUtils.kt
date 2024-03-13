package com.sungkyul.synergy.utils

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation
import androidx.core.view.updateLayoutParams
import com.sungkyul.synergy.R

const val TOUCH_DOWN_ELASTIC_BUTTON_DURATION = 250L
const val SPRING_BUTTON_DURATION = 150L
const val APPEARING_BUTTON_DURATION = 250L

class AnimUtils {
    companion object {
        // 버튼의 터치 애니메이션을 초기화하는 함수
        fun initTouchButtonAnimation(view: View) {
            view.background.alpha = 0
        }

        // 버튼의 터치 다운 애니메이션을 시작하는 함수
        fun startTouchDownButtonAnimation(context: Context, view: View) {
            val animator = AnimatorInflater.loadAnimator(context, R.animator.touch_down_button)
            animator.setTarget(view.background)
            animator.start()
        }

        // 버튼의 터치 업 애니메이션을 시작하는 함수
        fun startTouchUpButtonAnimation(context: Context, view: View) {
            val animator = AnimatorInflater.loadAnimator(context, R.animator.touch_up_button)
            animator.setTarget(view.background)
            animator.start()
        }

        // 탄성 있는 버튼의 터치 애니메이션을 초기화하는 함수
        fun initTouchElasticButtonAnimation(view: View) {
            view.background.alpha = 0
        }

        // 탄성 있는 버튼의 터치 다운 애니메이션을 시작하는 함수
        fun startTouchDownElasticButtonAnimation(context: Context, view: View) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.touch_down_elastic_button)
            animation.duration = TOUCH_DOWN_ELASTIC_BUTTON_DURATION
            animation.interpolator = DecelerateInterpolator()
            view.startAnimation(animation)

            startTouchDownButtonAnimation(context, view)
        }

        // 탄성 있는 버튼의 터치 업 애니메이션을 시작하는 함수
        fun startTouchUpElasticButtonAnimation(context: Context, view: View) {
            startTouchUpButtonAnimation(context, view)
        }

        // 용수철 버튼의 터치 다운 애니메이션을 시작하는 함수
        fun startTouchDownSpringButtonAnimation(view: View) {
            startObjectAnimatorOfFloat(view, "scaleX", view.scaleX, 0.75f, SPRING_BUTTON_DURATION, DecelerateInterpolator())
            startObjectAnimatorOfFloat(view, "scaleY", view.scaleY, 0.75f, SPRING_BUTTON_DURATION, DecelerateInterpolator())
        }

        // 용수철 버튼의 터치 업 애니메이션을 시작하는 함수
        fun startTouchUpSpringButtonAnimation(view: View) {
            startObjectAnimatorOfFloat(view, "scaleX", view.scaleX, 1.0f, SPRING_BUTTON_DURATION, OvershootInterpolator())
            startObjectAnimatorOfFloat(view, "scaleY", view.scaleY, 1.0f, SPRING_BUTTON_DURATION, OvershootInterpolator())
        }

        // 버튼의 등장 애니메이션을 시작하는 함수
        fun startAppearingButtonAnimation(view: View) {
            startObjectAnimatorOfFloat(view, "scaleX", view.scaleX, 1.0f, APPEARING_BUTTON_DURATION, DecelerateInterpolator())
            startObjectAnimatorOfFloat(view, "scaleY", view.scaleY, 1.0f, APPEARING_BUTTON_DURATION, DecelerateInterpolator())
            startObjectAnimatorOfFloat(view, "alpha", view.alpha, 1.0f, APPEARING_BUTTON_DURATION, DecelerateInterpolator())
        }

        // 버튼의 퇴장 애니메이션을 시작하는 함수
        fun startDisappearingButtonAnimation(view: View) {
            startObjectAnimatorOfFloat(view, "scaleX", view.scaleX, 0.5f, APPEARING_BUTTON_DURATION, DecelerateInterpolator())
            startObjectAnimatorOfFloat(view, "scaleY", view.scaleY, 0.5f, APPEARING_BUTTON_DURATION, DecelerateInterpolator())
            startObjectAnimatorOfFloat(view, "alpha", view.alpha, 0.0f, APPEARING_BUTTON_DURATION, DecelerateInterpolator())
        }

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

            val animation = ScaleAnimation(
                startScale, endScale,
                startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f, // 중심점 X
                Animation.RELATIVE_TO_SELF, 0.5f // 중심점 Y
            )
            animation.duration = duration
            animation.interpolator = interpolator
            //animation.fillAfter = true // 애니메이션 종료 후에도 유지시킨다. (주의: View.GONE을 해도 안 사라지는 문제점이 있다.)
            view.startAnimation(animation)
        }

        // (duration)ms 동안 view의 layout_height가 startHeight에서 endHeight로 바뀌는 함수
        // startScale, endScale의 범위는 [0.0f, 1.0f]이다.
        fun startHeightAnimation(
            view: View,
            duration: Long,
            startHeight: Int,
            endHeight: Int
        ) {
            // Ease-Out
            val interpolator = DecelerateInterpolator()

            val animator = ValueAnimator.ofInt(startHeight, endHeight)
            animator.addUpdateListener {
                view.updateLayoutParams {
                    height = it.animatedValue as Int
                }
            }

            animator.duration = duration
            animator.interpolator = interpolator
            animator.start()
        }

        // ObjectAnimator.ofFloat를 편하게 사용하려고 만든 함수
        fun startObjectAnimatorOfFloat(view: View, target: String, value1: Float, value2: Float, duration: Long, interpolator: Interpolator = LinearInterpolator()): ObjectAnimator {
            val animator = ObjectAnimator.ofFloat(view, target, value1, value2).apply {
                this.duration = duration
                this.interpolator = interpolator
                start()
            }
            return animator
        }

        // ValueAnimator.ofFloat를 편하게 사용하려고 만든 함수
        fun startValueAnimatorOfFloat(update: (Float) -> Unit, value1: Float, value2: Float, duration: Long, interpolator: Interpolator = LinearInterpolator()): ValueAnimator {
            val animator = ValueAnimator.ofFloat(value1, value2)
            animator.addUpdateListener {
                update(it.animatedValue as Float)
            }
            animator.duration = duration
            animator.interpolator = interpolator
            animator.start()
            return animator
        }

        // dp에서 px로 바꾸는 함수
        fun dpToPx(context: Context, dp: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
        }

        // px에서 dp로 바꾸는 함수
        fun pxToDp(context: Context, px: Float): Float {
            return px/context.resources.displayMetrics.density
        }
    }
}

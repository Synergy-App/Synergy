package com.sungkyul.synergy.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.FragmentEduScreenBinding

class EduScreenFragment : Fragment() {
    private lateinit var binding: FragmentEduScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEduScreenBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun startObjectAnimationOfDialog(
        duration: Long,
        interpolator: Interpolator,
        startAlpha: Float,
        endAlpha: Float,
        startScaleX: Float,
        endScaleX: Float,
        startScaleY: Float,
        endScaleY: Float,
        startTranslationX: Float,
        endTranslationX: Float,
        startTranslationY: Float,
        endTranslationY: Float
    ) {
        AnimUtil.startObjectAnimatorOfFloat(binding.eduDialog, "alpha", startAlpha, endAlpha, duration, interpolator)
        AnimUtil.startObjectAnimatorOfFloat(binding.eduDialog, "scaleX", startScaleX, endScaleX, duration, interpolator)
        AnimUtil.startObjectAnimatorOfFloat(binding.eduDialog, "scaleY", startScaleY, endScaleY, duration, interpolator)
        AnimUtil.startObjectAnimatorOfFloat(binding.eduDialog, "translationX", startTranslationX, endTranslationX, duration, interpolator)
        AnimUtil.startObjectAnimatorOfFloat(binding.eduDialog, "translationY", startTranslationY, endTranslationY, duration, interpolator)
    }

    private fun startValueAnimationOfDialog(
        duration: Long,
        interpolator: Interpolator,
        startTopMargin: Float,
        endTopMargin: Float,
        startBottomMargin: Float,
        endBottomMargin: Float,
        startLeftMargin: Float,
        endLeftMargin: Float,
        startRightMargin: Float,
        endRightMargin: Float
    ) {
        AnimUtil.startValueAnimatorOfFloat({
            binding.eduDialog.updateLayoutParams<FrameLayout.LayoutParams> {
                topMargin = it.toInt()
            }
        }, startTopMargin, endTopMargin, duration, interpolator)

        AnimUtil.startValueAnimatorOfFloat({
            binding.eduDialog.updateLayoutParams<FrameLayout.LayoutParams> {
                bottomMargin = it.toInt()
            }
        }, startBottomMargin, endBottomMargin, duration, interpolator)

        AnimUtil.startValueAnimatorOfFloat({
            binding.eduDialog.updateLayoutParams<FrameLayout.LayoutParams> {
                marginStart = it.toInt()
            }
        }, startLeftMargin, endLeftMargin, duration, interpolator)

        AnimUtil.startValueAnimatorOfFloat({
            binding.eduDialog.updateLayoutParams<FrameLayout.LayoutParams> {
                marginEnd = it.toInt()
            }
        }, startRightMargin, endRightMargin, duration, interpolator)
    }

    private fun startObjectAnimationOfCovers(
        duration: Long,
        interpolator: Interpolator,
        startAlpha: Float,
        endAlpha: Float
    ) {
        AnimUtil.startObjectAnimatorOfFloat(binding.eduCoverTop, "alpha", startAlpha, endAlpha, duration, interpolator)
        AnimUtil.startObjectAnimatorOfFloat(binding.eduCoverBottom, "alpha", startAlpha, endAlpha, duration, interpolator)
        AnimUtil.startObjectAnimatorOfFloat(binding.eduCoverStart, "alpha", startAlpha, endAlpha, duration, interpolator)
        AnimUtil.startObjectAnimatorOfFloat(binding.eduCoverEnd, "alpha", startAlpha, endAlpha, duration, interpolator)
    }

    private fun startValueAnimationOfCovers(
        duration: Long,
        interpolator: Interpolator,
        startTop: Float,
        endTop: Float,
        startBottom: Float,
        endBottom: Float,
        startLeft: Float,
        endLeft: Float,
        startRight: Float,
        endRight: Float
    ) {
        AnimUtil.startValueAnimatorOfFloat({
            binding.eduCoverTop.updateLayoutParams<RelativeLayout.LayoutParams> {
                height = it.toInt()
            }
        }, startTop, endTop, duration, interpolator)

        AnimUtil.startValueAnimatorOfFloat({
            binding.eduCoverBottom.updateLayoutParams<RelativeLayout.LayoutParams> {
                height = it.toInt()
            }
        }, startBottom, endBottom, duration, interpolator)

        AnimUtil.startValueAnimatorOfFloat({
            binding.eduCoverStart.updateLayoutParams<RelativeLayout.LayoutParams> {
                width = it.toInt()
            }
        }, startLeft, endLeft, duration, interpolator)

        AnimUtil.startValueAnimatorOfFloat({
            binding.eduCoverEnd.updateLayoutParams<RelativeLayout.LayoutParams> {
                width = it.toInt()
            }
        }, startRight, endRight, duration, interpolator)
    }

    // 설명 다이얼로그를 보여주는 함수
    fun showDialog() {
        startObjectAnimationOfDialog(
            250,
            DecelerateInterpolator(),
            0.0f,
            1.0f,
            0.9f,
            1.0f,
            0.9f,
            1.0f,
            AnimUtil.dpToPx(binding.root.context, 0.0f),
            AnimUtil.dpToPx(binding.root.context, 0.0f),
            AnimUtil.dpToPx(binding.root.context, 100.0f),
            AnimUtil.dpToPx(binding.root.context, 0.0f)
        )
    }

    // 설명 다이얼로그를 숨기는 함수
    fun hideDialog() {
        startObjectAnimationOfDialog(
            250,
            DecelerateInterpolator(),
            1.0f,
            0.0f,
            1.0f,
            0.9f,
            1.0f,
            0.9f,
            AnimUtil.dpToPx(binding.root.context, 0.0f),
            AnimUtil.dpToPx(binding.root.context, 0.0f),
            AnimUtil.dpToPx(binding.root.context, 0.0f),
            AnimUtil.dpToPx(binding.root.context, -100.0f)
        )
    }

    // 설명 다이얼로그를 움직이는 함수
    fun translateDialog(
        duration: Long,
        startTopMargin: Int,
        endTopMargin: Int,
        startBottomMargin: Int,
        endBottomMargin: Int,
        startLeftMargin: Int,
        endLeftMargin: Int,
        startRightMargin: Int,
        endRightMargin: Int
    ) {
        startValueAnimationOfDialog(
            duration,
            AccelerateDecelerateInterpolator(),
            AnimUtil.dpToPx(binding.root.context, startTopMargin.toFloat()),
            AnimUtil.dpToPx(binding.root.context, endTopMargin.toFloat()),
            AnimUtil.dpToPx(binding.root.context, startBottomMargin.toFloat()),
            AnimUtil.dpToPx(binding.root.context, endBottomMargin.toFloat()),
            AnimUtil.dpToPx(binding.root.context, startLeftMargin.toFloat()),
            AnimUtil.dpToPx(binding.root.context, endLeftMargin.toFloat()),
            AnimUtil.dpToPx(binding.root.context, startRightMargin.toFloat()),
            AnimUtil.dpToPx(binding.root.context, endRightMargin.toFloat())
        )
    }

    // 커버들을 보여주는 함수
    fun showCovers() {
        startObjectAnimationOfCovers(
            250,
            DecelerateInterpolator(),
            0.0f,
            1.0f
        )
    }

    // 커버들을 숨기는 함수
    fun hideCovers() {
        startObjectAnimationOfCovers(
            250,
            DecelerateInterpolator(),
            1.0f,
            0.0f
        )
    }

    // 커버들을 움직이는 함수
    fun translateCovers(
        duration: Long,
        startTop: Int,
        endTop: Int,
        startBottom: Int,
        endBottom: Int,
        startLeft: Int,
        endLeft: Int,
        startRight: Int,
        endRight: Int
    ) {
        startValueAnimationOfCovers(
            duration,
            AccelerateDecelerateInterpolator(),
            AnimUtil.dpToPx(binding.root.context, startTop.toFloat()),
            AnimUtil.dpToPx(binding.root.context, endTop.toFloat()),
            AnimUtil.dpToPx(binding.root.context, startBottom.toFloat()),
            AnimUtil.dpToPx(binding.root.context, endBottom.toFloat()),
            AnimUtil.dpToPx(binding.root.context, startLeft.toFloat()),
            AnimUtil.dpToPx(binding.root.context, endLeft.toFloat()),
            AnimUtil.dpToPx(binding.root.context, startRight.toFloat()),
            AnimUtil.dpToPx(binding.root.context, endRight.toFloat())
        )
    }
}

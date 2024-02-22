package com.sungkyul.synergy.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.FragmentEduScreenBinding

class EduScreenFragment(private val eduListener: EduListener) : Fragment() {
    private lateinit var binding: FragmentEduScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEduScreenBinding.inflate(inflater, container, false)

        // onCreateView가 호출되었다는 걸 리스너를 통해 알린다.
        eduListener.onCreateViewFinished()

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
        AnimUtils.startObjectAnimatorOfFloat(binding.eduDialog, "alpha", startAlpha, endAlpha, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.eduDialog, "scaleX", startScaleX, endScaleX, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.eduDialog, "scaleY", startScaleY, endScaleY, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.eduDialog, "translationX", startTranslationX, endTranslationX, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.eduDialog, "translationY", startTranslationY, endTranslationY, duration, interpolator)
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
        AnimUtils.startValueAnimatorOfFloat({
            binding.eduDialog.updateLayoutParams<FrameLayout.LayoutParams> {
                topMargin = it.toInt()
            }
        }, startTopMargin, endTopMargin, duration, interpolator)

        AnimUtils.startValueAnimatorOfFloat({
            binding.eduDialog.updateLayoutParams<FrameLayout.LayoutParams> {
                bottomMargin = it.toInt()
            }
        }, startBottomMargin, endBottomMargin, duration, interpolator)

        AnimUtils.startValueAnimatorOfFloat({
            binding.eduDialog.updateLayoutParams<FrameLayout.LayoutParams> {
                marginStart = it.toInt()
            }
        }, startLeftMargin, endLeftMargin, duration, interpolator)

        AnimUtils.startValueAnimatorOfFloat({
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
        AnimUtils.startObjectAnimatorOfFloat(binding.eduCoverTop, "alpha", startAlpha, endAlpha, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.eduCoverBottom, "alpha", startAlpha, endAlpha, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.eduCoverStart, "alpha", startAlpha, endAlpha, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.eduCoverEnd, "alpha", startAlpha, endAlpha, duration, interpolator)
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
        AnimUtils.startValueAnimatorOfFloat({
            binding.eduCoverTop.updateLayoutParams<RelativeLayout.LayoutParams> {
                height = it.toInt()
            }
        }, startTop, endTop, duration, interpolator)

        AnimUtils.startValueAnimatorOfFloat({
            binding.eduCoverBottom.updateLayoutParams<RelativeLayout.LayoutParams> {
                height = it.toInt()
            }
        }, startBottom, endBottom, duration, interpolator)

        AnimUtils.startValueAnimatorOfFloat({
            binding.eduCoverStart.updateLayoutParams<RelativeLayout.LayoutParams> {
                width = it.toInt()
            }
        }, startLeft, endLeft, duration, interpolator)

        AnimUtils.startValueAnimatorOfFloat({
            binding.eduCoverEnd.updateLayoutParams<RelativeLayout.LayoutParams> {
                width = it.toInt()
            }
        }, startRight, endRight, duration, interpolator)
    }

    // 제목을 보여주는 함수
    fun showTitle() {
        binding.eduTitle.visibility = TextView.VISIBLE
        binding.separator.visibility = TextView.VISIBLE
    }

    // 제목을 숨기는 함수
    fun hideTitle() {
        binding.eduTitle.visibility = TextView.GONE
        binding.separator.visibility = TextView.GONE
    }

    // 다이얼로그의 제목과 내용을 업데이트하는 함수
    fun updateDialogTitleAndContent(title: String, titleGravity: Int, content: String, contentGravity: Int) {
        binding.eduTitle.text = title
        binding.eduTitle.gravity = titleGravity
        binding.eduContent.text = content
        binding.eduContent.gravity = contentGravity
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
            AnimUtils.dpToPx(binding.root.context, 0.0f),
            AnimUtils.dpToPx(binding.root.context, 0.0f),
            AnimUtils.dpToPx(binding.root.context, 100.0f),
            AnimUtils.dpToPx(binding.root.context, 0.0f)
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
            AnimUtils.dpToPx(binding.root.context, 0.0f),
            AnimUtils.dpToPx(binding.root.context, 0.0f),
            AnimUtils.dpToPx(binding.root.context, 0.0f),
            AnimUtils.dpToPx(binding.root.context, -100.0f)
        )
    }

    // 설명 다이얼로그를 움직이는 함수
    // 단위는 dp이다.
    fun translateDialog(
        duration: Long,
        dialogTop: Int,
        dialogBottom: Int,
        dialogStart: Int,
        dialogEnd: Int
    ) {
        startValueAnimationOfDialog(
            duration,
            AccelerateDecelerateInterpolator(),
            binding.eduDialog.marginTop.toFloat(),
            AnimUtils.dpToPx(binding.root.context, dialogTop.toFloat()),
            binding.eduDialog.marginBottom.toFloat(),
            AnimUtils.dpToPx(binding.root.context, dialogBottom.toFloat()),
            binding.eduDialog.marginStart.toFloat(),
            AnimUtils.dpToPx(binding.root.context, dialogStart.toFloat()),
            binding.eduDialog.marginEnd.toFloat(),
            AnimUtils.dpToPx(binding.root.context, dialogEnd.toFloat())
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
        coverTop: Int,
        coverBottom: Int,
        coverStart: Int,
        coverEnd: Int
    ) {
        startValueAnimationOfCovers(
            duration,
            AccelerateDecelerateInterpolator(),
            binding.eduCoverTop.height.toFloat(),
            AnimUtils.dpToPx(binding.root.context, coverTop.toFloat()),
            binding.eduCoverBottom.height.toFloat(),
            AnimUtils.dpToPx(binding.root.context, coverBottom.toFloat()),
            binding.eduCoverStart.width.toFloat(),
            AnimUtils.dpToPx(binding.root.context, coverStart.toFloat()),
            binding.eduCoverEnd.width.toFloat(),
            AnimUtils.dpToPx(binding.root.context, coverEnd.toFloat())
        )
    }
}

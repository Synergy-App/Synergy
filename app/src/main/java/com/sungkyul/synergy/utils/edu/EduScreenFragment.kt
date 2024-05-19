package com.sungkyul.synergy.utils.edu

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.text.parseAsHtml
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.FragmentEduScreenBinding
import com.sungkyul.synergy.utils.AnimUtils
import com.sungkyul.synergy.utils.DisplayUtils
import com.sungkyul.synergy.utils.GeometryUtils
import kotlin.collections.set

class EduScreenFragment : Fragment() {
    data class Hand(
        var view: ImageView,
        var animator: AnimatorSet
    )

    private lateinit var binding: FragmentEduScreenBinding
    private lateinit var bitmap: Bitmap
    private var eduListener: EduListener? = null
    private val canvas = Canvas()
    private val animatorMap = hashMapOf<String, ValueAnimator>()

    // 페인트
    private val clearPaint = Paint().apply {
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }
    private val coverPaint = Paint().apply {
        color = Color.BLACK
        alpha = 0
    }
    private val boxPaint = Paint().apply {
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        alpha = 0
    }
    private val boxBorderPaint = Paint().apply {
        style = Paint.Style.STROKE
        alpha = 0
    }
    private val arrowPaint = Paint().apply {
        strokeWidth = 20.0f
        color = Color.WHITE
        alpha = 0
    }

    private val toggleHandDuration = 350L
    private val toggleHandInterpolator = DecelerateInterpolator()
    private val coverMaxAlpha = 128
    private val boxCornerRadius = 50.0f
    private val boxBorderCornerRadius = 50.0f
    private val arrowEndSize = 30.0f

    private var boxPadding = 35.0f

    private var boxLeft = 0.0f
    private var boxTop = 0.0f
    private var boxRight = 0.0f
    private var boxBottom = 0.0f
    private var arrowStartX = 0.0f
    private var arrowStartY = 0.0f
    private var arrowEndX = 0.0f
    private var arrowEndY = 0.0f
    private val hands = HashMap<String, Hand>()

    // 현재 캔버스 정보
    private var currentDialogCenterX = 0.0f
    private var currentDialogCenterY = 0.0f
    private var currentBoxCenterX = 0.0f
    private var currentBoxCenterY = 0.0f
    private var currentBoxBorderTopY = 0.0f
    private var currentBoxBorderBottomY = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEduScreenBinding.inflate(inflater, container, false)

        binding.dialog.alpha = 0.0f
        binding.imageDialog.alpha = 0.0f

        binding.root.post {
            // 캔버스를 생성한다.
            bitmap = Bitmap.createBitmap(binding.root.width, binding.root.height, Bitmap.Config.ARGB_8888)
            canvas.setBitmap(bitmap)
            binding.canvas.setImageBitmap(bitmap)

            eduListener?.onPosted()
        }

        return binding.root
    }

    // 이 함수로 Animator를 등록하여 사용하면, 화면을 연속으로 빠르게 눌렀을 때 애니메이션이 끊기는 현상을 해결할 수 있다.
    private fun registerAnimator(id: String, animator: ValueAnimator) {
        animatorMap[id]?.cancel()
        animatorMap[id] = animator
    }

    private fun startObjectAnimatorOfDialog(
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
        AnimUtils.startObjectAnimatorOfFloat(
            binding.dialog,
            "alpha",
            startAlpha,
            endAlpha,
            duration,
            interpolator
        )
        AnimUtils.startObjectAnimatorOfFloat(
            binding.dialog,
            "scaleX",
            startScaleX,
            endScaleX,
            duration,
            interpolator
        )
        AnimUtils.startObjectAnimatorOfFloat(
            binding.dialog,
            "scaleY",
            startScaleY,
            endScaleY,
            duration,
            interpolator
        )
        AnimUtils.startObjectAnimatorOfFloat(
            binding.dialog,
            "translationX",
            startTranslationX,
            endTranslationX,
            duration,
            interpolator
        )
        AnimUtils.startObjectAnimatorOfFloat(
            binding.dialog,
            "translationY",
            startTranslationY,
            endTranslationY,
            duration,
            interpolator
        )
    }

    private fun startValueAnimatorOfDialog(
        dialog: LinearLayout,
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
        registerAnimator("dialog", AnimUtils.startValueAnimatorOfFloat({
            dialog.updateLayoutParams<FrameLayout.LayoutParams> {
                topMargin = (startTopMargin + (endTopMargin - startTopMargin) * it).toInt()
                bottomMargin =
                    (startBottomMargin + (endBottomMargin - startBottomMargin) * it).toInt()
                marginStart = (startLeftMargin + (endLeftMargin - startLeftMargin) * it).toInt()
                marginEnd = (startRightMargin + (endRightMargin - startRightMargin) * it).toInt()
            }
        }, 0.0f, 1.0f, duration, interpolator))
    }

    private fun startShowHandAnimationSet(imageView: ImageView, nextAnimator: AnimatorSet): AnimatorSet {
        val fromValue = 0.0f
        val toValue = 1.0f

        // 스케일을 확대한다.
        val scaleSet = AnimatorSet().apply {
            duration = toggleHandDuration
            interpolator = toggleHandInterpolator
            playTogether(
                ObjectAnimator.ofFloat(imageView, "scaleX", fromValue, toValue),
                ObjectAnimator.ofFloat(imageView, "scaleY", fromValue, toValue)
            )
        }

        val animatorSet = AnimatorSet().apply {
            playSequentially(
                scaleSet,
                nextAnimator
            )
            addListener(object: Animator.AnimatorListener {
                var canceled = false
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    // 애니메이션이 취소될 때까지 nextAnimator에서부터 무한 반복한다.
                    if(!canceled) {
                        currentPlayTime = toggleHandDuration
                        start()
                    }
                }
                override fun onAnimationCancel(animation: Animator) {
                    canceled = true
                }
                override fun onAnimationRepeat(animation: Animator) {}
            })
            start()
        }

        return animatorSet
    }

    private fun startHideHandAnimationSet(imageView: ImageView): AnimatorSet {
        val fromValue = imageView.scaleX
        val toValue = 0.0f

        // 스케일 축소
        val scaleSet = AnimatorSet().apply {
            duration = toggleHandDuration
            interpolator = toggleHandInterpolator
            playTogether(
                ObjectAnimator.ofFloat(imageView, "scaleX", fromValue, toValue),
                ObjectAnimator.ofFloat(imageView, "scaleY", fromValue, toValue)
            )

            start()
        }

        return scaleSet
    }

    fun draw() {
        // Clear canvas
        canvas.drawRect(0.0f, 0.0f, binding.root.width.toFloat(), binding.root.height.toFloat(), clearPaint)

        // Draw cover
        canvas.drawRect(
            0.0f,
            0.0f,
            binding.root.width.toFloat(),
            binding.root.height.toFloat(),
            coverPaint
        )
        // Draw box
        if(boxPaint.alpha > 0) {
            canvas.drawRoundRect(
                boxLeft,
                boxTop,
                boxRight,
                boxBottom,
                boxCornerRadius,
                boxCornerRadius,
                boxPaint
            )
        }
        // Draw box border
        canvas.drawRoundRect(
            boxLeft-boxPadding,
            boxTop-boxPadding,
            boxRight+boxPadding,
            boxBottom+boxPadding,
            boxBorderCornerRadius,
            boxBorderCornerRadius,
            boxBorderPaint
        )

        // Draw arrow
        canvas.drawLine(arrowStartX, arrowStartY, arrowEndX, arrowEndY, arrowPaint)
        // Draw arrow end
        canvas.drawCircle(arrowEndX, arrowEndY, arrowEndSize, arrowPaint)

        // Apply canvas
        if(::bitmap.isInitialized) {
            binding.canvas.setImageBitmap(bitmap)
        }
    }

    fun setEduListener(l: EduListener) {
        eduListener = l
    }

    fun setDialogTitle(text: String, gravity: Int, color: Int) {
        binding.dialogTitle.text = text
        binding.dialogTitle.gravity = gravity
        binding.dialogTitle.setTextColor(ContextCompat.getColor(requireContext(), color))
    }

    fun showDialogTitle() {
        binding.dialogTitle.visibility = TextView.VISIBLE
        binding.dialogSeparator.visibility = TextView.VISIBLE
    }

    fun hideDialogTitle() {
        binding.dialogTitle.visibility = TextView.GONE
        binding.dialogSeparator.visibility = TextView.GONE
    }

    fun setDialogContent(text: String, gravity: Int, color: Int) {
        binding.dialogContent.text = text.parseAsHtml()
        binding.dialogContent.gravity = gravity
        binding.dialogContent.setTextColor(ContextCompat.getColor(requireContext(), color))
    }

    fun setDialogTitleFont(font: Int) {
        binding.dialogTitle.typeface = resources.getFont(font)
    }

    fun setDialogContentFont(font: Int) {
        binding.dialogContent.typeface = resources.getFont(font)
    }

    fun setDialogTitleSize(size: Float) {
        binding.dialogTitle.textSize = size
    }

    fun setDialogContentSize(size: Float) {
        binding.dialogContent.textSize = size
    }

    fun setDialogSeparatorColor(color: Int) {
        binding.dialogSeparator.setBackgroundColor(ContextCompat.getColor(requireContext(), color))
    }

    fun setDialogSeparatorWidth(width: Int) {
        binding.dialogSeparator.updateLayoutParams {
            height = width
        }
    }

    fun setDialogBackground(background: Int) {
        binding.dialog.setBackgroundResource(background)
    }

    fun setBoxPadding(boxPadding: Float) {
        this.boxPadding = boxPadding
    }

    fun setBoxBorderColor(color: Int) {
        boxBorderPaint.color = ContextCompat.getColor(requireContext(), color)
    }

    fun setBoxBorderWidth(boxBorderWidth: Float) {
        boxBorderPaint.strokeWidth = boxBorderWidth
    }

    fun showDialog() {
        startObjectAnimatorOfDialog(
            DIALOG_TOGGLE_DURATION,
            DecelerateInterpolator(),
            0.0f,
            1.0f,
            0.9f,
            1.0f,
            0.9f,
            1.0f,
            DisplayUtils.dpToPx(binding.root.context, 0.0f),
            DisplayUtils.dpToPx(binding.root.context, 0.0f),
            DisplayUtils.dpToPx(binding.root.context, 100.0f),
            DisplayUtils.dpToPx(binding.root.context, 0.0f)
        )
    }

    fun hideDialog() {
        startObjectAnimatorOfDialog(
            DIALOG_TOGGLE_DURATION,
            DecelerateInterpolator(),
            1.0f,
            0.0f,
            1.0f,
            0.9f,
            1.0f,
            0.9f,
            DisplayUtils.dpToPx(binding.root.context, 0.0f),
            DisplayUtils.dpToPx(binding.root.context, 0.0f),
            DisplayUtils.dpToPx(binding.root.context, 0.0f),
            DisplayUtils.dpToPx(binding.root.context, -100.0f)
        )
    }

    fun showCover() {
        AnimUtils.startValueAnimatorOfFloat({
            coverPaint.alpha = (it * coverMaxAlpha).toInt()
            draw()
        }, 0.0f, 1.0f, COVER_TOGGLE_DURATION)
    }

    fun hideCover() {
        AnimUtils.startValueAnimatorOfFloat({
            coverPaint.alpha = (it * coverMaxAlpha).toInt()
            draw()
        }, 1.0f, 0.0f, COVER_TOGGLE_DURATION)
    }

    fun showBox() {
        boxPaint.alpha = 255
    }

    fun hideBox() {
        boxPaint.alpha = 0
    }

    fun showBoxBorder() {
        AnimUtils.startValueAnimatorOfFloat({
            boxBorderPaint.alpha = (it * 255).toInt()
            draw()
        }, 0.0f, 1.0f, BOX_BORDER_TOGGLE_DURATION)
    }

    fun hideBoxBorder() {
        AnimUtils.startValueAnimatorOfFloat({
            boxBorderPaint.alpha = (it * 255).toInt()
            draw()
        }, 1.0f, 0.0f, BOX_BORDER_TOGGLE_DURATION)
    }

    fun setCoverBackgroundColor(color: Int) {
        val alpha = coverPaint.alpha
        coverPaint.color = ContextCompat.getColor(requireContext(), color)
        coverPaint.alpha = alpha
    }

    fun showArrow() {
        AnimUtils.startValueAnimatorOfFloat({
            arrowPaint.alpha = (it * 255).toInt()
            draw()
        }, 0.0f, 1.0f, ARROW_TOGGLE_DURATION)
    }

    fun hideArrow() {
        arrowPaint.alpha = 0

        /*AnimUtils.startValueAnimatorOfFloat({
            arrowPaint.alpha = (it*255).toInt()
            draw()
        }, 1.0f, 0.0f, showHideDuration)*/
    }

    fun translateDialog(
        topDp: Float,
        bottomDp: Float,
        startDp: Float,
        endDp: Float,
        duration: Long = DIALOG_MOVEMENT_DURATION
    ) {
        // dp -> px
        val top = DisplayUtils.dpToPx(binding.root.context, topDp)
        val bottom = DisplayUtils.dpToPx(binding.root.context, bottomDp)
        val start = DisplayUtils.dpToPx(binding.root.context, startDp)
        val end = DisplayUtils.dpToPx(binding.root.context, endDp)

        startValueAnimatorOfDialog(
            binding.dialog,
            duration,
            AccelerateDecelerateInterpolator(),
            binding.dialog.marginTop.toFloat(),
            top,
            binding.dialog.marginBottom.toFloat(),
            bottom,
            binding.dialog.marginStart.toFloat(),
            start,
            binding.dialog.marginEnd.toFloat(),
            end
        )

        currentDialogCenterX = start+(binding.root.width-start-end)/2
        currentDialogCenterY = top+(binding.root.height-top-bottom)/2
    }
    fun translateBox(
        leftDp: Float,
        topDp: Float,
        rightDp: Float,
        bottomDp: Float,
        duration: Long = BOX_MOVEMENT_DURATION
    ) {
        // dp -> px
        val left = DisplayUtils.dpToPx(binding.root.context, leftDp)
        val top = DisplayUtils.dpToPx(binding.root.context, topDp)
        val right = DisplayUtils.dpToPx(binding.root.context, rightDp)
        val bottom = DisplayUtils.dpToPx(binding.root.context, bottomDp)

        val startLeft = boxLeft
        val startTop = boxTop
        val startRight = boxRight
        val startBottom = boxBottom

        registerAnimator("box", AnimUtils.startValueAnimatorOfFloat({
            boxLeft = GeometryUtils.linear(startLeft, left, it)
            boxTop = GeometryUtils.linear(startTop, top, it)
            boxRight = GeometryUtils.linear(startRight, right, it)
            boxBottom = GeometryUtils.linear(startBottom, bottom, it)
            draw()
        }, 0.0f, 1.0f, duration, AccelerateDecelerateInterpolator()))

        currentBoxCenterX = GeometryUtils.linear(left, right, 0.5f)
        currentBoxCenterY = GeometryUtils.linear(top, bottom, 0.5f)
        currentBoxBorderTopY = top-boxPadding
        currentBoxBorderBottomY = bottom+boxPadding
    }

    // 화살표의 시작점이 다이얼로그의 중심을 향해 이동하도록 만든다.
    fun translateArrowStart() {
        val startArrowStartX = arrowStartX
        val startArrowStartY = arrowStartY

        registerAnimator("arrow_start", AnimUtils.startValueAnimatorOfFloat({
            arrowStartX = GeometryUtils.linear(startArrowStartX, currentDialogCenterX, it)
            arrowStartY = GeometryUtils.linear(startArrowStartY, currentDialogCenterY, it)
            draw()
        }, 0.0f, 1.0f, ARROW_MOVEMENT_DURATION, AccelerateDecelerateInterpolator()))
    }

    // 화살표의 끝점이 다이얼로그의 중심을 향해 이동하도록 만든다.
    fun translateArrowEndToDialog() {
        val startArrowEndX = arrowEndX
        val startArrowEndY = arrowEndY

        registerAnimator("arrow_end", AnimUtils.startValueAnimatorOfFloat({
            arrowEndX = GeometryUtils.linear(startArrowEndX, currentDialogCenterX, it)
            arrowEndY = GeometryUtils.linear(startArrowEndY, currentDialogCenterY, it)
            draw()
        }, 0.0f, 1.0f, ARROW_MOVEMENT_DURATION, AccelerateDecelerateInterpolator()))
    }

    // 화살표의 끝점이 박스를 향해 이동하도록 만든다.
    // 화살표 끝점의 x좌표는 박스 중심의 x좌표로 이동한다.
    // 화살표 끝점의 y좌표는 만일 '다이얼로그 중심의 y좌표 < 박스 중심의 y좌표'이면 박스 테두리의 top y좌표로 이동하고, 아니면 bottom y좌표로 이동한다.
    fun translateArrowEndToBox() {
        val startArrowEndX = arrowEndX
        val startArrowEndY = arrowEndY

        registerAnimator("arrow_end", AnimUtils.startValueAnimatorOfFloat({
            arrowEndX = GeometryUtils.linear(startArrowEndX, currentBoxCenterX, it)
            arrowEndY = GeometryUtils.linear(
                startArrowEndY,
                if (currentDialogCenterY < currentBoxCenterY) currentBoxBorderTopY else currentBoxBorderBottomY,
                it
            )
            draw()
        }, 0.0f, 1.0f, ARROW_MOVEMENT_DURATION, OvershootInterpolator(0.5f)))
    }

    // 오른쪽 아래에 그림자가 적용된 비트맵을 만든다.
    private fun makeShadowedBitmap(source: Bitmap, x: Int, y: Int, color: Int): Bitmap {
        val shadow = source.copy(Bitmap.Config.ARGB_8888, true)
        val result = Bitmap.createBitmap(source.width+x, source.height+y, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)

        // 그림자를 만든다.
        val pixels = IntArray(shadow.width*shadow.height)
        shadow.getPixels(pixels, 0, shadow.width, 0, 0, shadow.width, shadow.height)
        for(i in pixels.indices) {
            if(pixels[i] != Color.TRANSPARENT) {
                pixels[i] = color
            }
        }
        shadow.setPixels(pixels, 0, shadow.width, 0, 0, shadow.width, shadow.height)

        // 그림자를 적용한다.
        canvas.drawBitmap(shadow, x.toFloat(), y.toFloat(), null)
        canvas.drawBitmap(source, 0.0f, 0.0f, null)

        return result
    }

    // 손을 추가한다.
    fun addHand(
        id: String,
        source: Int,
        xDp: Float,
        yDp: Float,
        widthDp: Float,
        heightDp: Float,
        rotation: Float,
        gesture: (Context, ImageView) -> AnimatorSet
    ): ImageView {
        val imageView = ImageView(context)

        val bitmap = makeShadowedBitmap(
            ResourcesCompat.getDrawable(resources, source, null)!!.toBitmap(),
            12,
            12,
            Color.argb(100, 0, 0, 0)
        )
        imageView.setImageBitmap(bitmap)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER

        binding.gestureLayout.addView(imageView)

        imageView.translationX = DisplayUtils.dpToPx(binding.root.context, xDp)
        imageView.translationY = DisplayUtils.dpToPx(binding.root.context, yDp)
        imageView.rotation = rotation
        imageView.updateLayoutParams<ViewGroup.LayoutParams> {
            this.width = DisplayUtils.dpToPx(binding.root.context, widthDp).toInt()
            this.height = DisplayUtils.dpToPx(binding.root.context, heightDp).toInt()
        }

        hands[id] = Hand(imageView, startShowHandAnimationSet(imageView, gesture(binding.root.context, imageView)))

        return imageView
    }

    // 손을 삭제한다.
    fun removeHand(id: String) {
        if(hands[id] != null) {
            hands[id]!!.animator.cancel()
            startHideHandAnimationSet(hands[id]!!.view)
            hands.remove(id)
        }
    }

    // 모든 손을 삭제한다.
    fun clearHands() {
        hands.forEach {
            removeHand(it.key)
        }
    }

    companion object {
        const val DIALOG_TOGGLE_DURATION = 250L
        const val COVER_TOGGLE_DURATION = 250L
        const val BOX_BORDER_TOGGLE_DURATION = 250L
        const val ARROW_TOGGLE_DURATION = 250L

        // TODO(visibility → color)
        const val COVER_COLOR_TRANSFORMATION_DURATION = 250L
        const val BOX_BORDER_COLOR_TRANSFORMATION_DURATION = 250L

        const val DIALOG_MOVEMENT_DURATION = 750L
        const val BOX_MOVEMENT_DURATION = 750L
        const val ARROW_MOVEMENT_DURATION = 750L
    }
}

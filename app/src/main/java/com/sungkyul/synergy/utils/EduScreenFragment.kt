package com.sungkyul.synergy.utils

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
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
import androidx.core.animation.addListener
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.FragmentEduScreenBinding
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.forEach
import kotlin.collections.hashMapOf
import kotlin.collections.indices
import kotlin.collections.set

class EduScreenFragment : Fragment() {
    data class Hand(
        var view: ImageView,
        var animator: AnimatorSet
    )

    private lateinit var binding: FragmentEduScreenBinding
    private lateinit var bitmap: Bitmap
    private val canvas = Canvas()
    private val animatorMap = hashMapOf<String, ValueAnimator>()

    private val clearPaint = Paint()
    private val coverPaint = Paint()
    private val boxPaint = Paint()
    private val boxStrokePaint = Paint()
    private val arrowPaint = Paint()

    // 알아서 파라미터 설정하세요.
    private val toggleDuration = 250L
    private val toggleHandDuration = 250L
    private val toggleHandInterpolator = DecelerateInterpolator()
    private val coverMaxAlpha = 128
    private val boxCornerRadius = 100.0f
    private val boxStrokeCornerRadius = 100.0f
    private val boxPadding = 35.0f
    private val arrowEndSize = 30.0f

    private var boxLeft = 0.0f
    private var boxTop = 0.0f
    private var boxRight = 0.0f
    private var boxBottom = 0.0f
    private var arrowStartX = 0.0f
    private var arrowStartY = 0.0f
    private var arrowEndX = 0.0f
    private var arrowEndY = 0.0f
    private val hands = HashMap<String, Hand>()

    private var currentDialogCenterX = 0.0f
    private var currentDialogCenterY = 0.0f
    private var currentBoxCenterX = 0.0f
    private var currentBoxCenterY = 0.0f
    private var currentBoxStrokeTopY = 0.0f
    private var currentBoxStrokeBottomY = 0.0f

    init {
        clearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

        coverPaint.color = Color.BLACK
        coverPaint.alpha = 0

        boxPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

        boxStrokePaint.style = Paint.Style.STROKE
        boxStrokePaint.strokeWidth = 20.0f
        boxStrokePaint.color = Color.rgb(69, 232, 188)
        boxStrokePaint.alpha = 0

        arrowPaint.strokeWidth = 20.0f
        arrowPaint.color = Color.WHITE
        arrowPaint.alpha = 0
    }

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
        }

        return binding.root
    }

    // 애니메이터를 등록한다.
    // 화면을 연속으로 빠르게 눌렀을 때, 애니메이션이 끊기는 현상을 해결할 수 있다.
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
        AnimUtils.startObjectAnimatorOfFloat(binding.dialog, "alpha", startAlpha, endAlpha, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.dialog, "scaleX", startScaleX, endScaleX, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.dialog, "scaleY", startScaleY, endScaleY, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.dialog, "translationX", startTranslationX, endTranslationX, duration, interpolator)
        AnimUtils.startObjectAnimatorOfFloat(binding.dialog, "translationY", startTranslationY, endTranslationY, duration, interpolator)
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
                topMargin = (startTopMargin+(endTopMargin-startTopMargin)*it).toInt()
                bottomMargin = (startBottomMargin+(endBottomMargin-startBottomMargin)*it).toInt()
                marginStart = (startLeftMargin+(endLeftMargin-startLeftMargin)*it).toInt()
                marginEnd = (startRightMargin+(endRightMargin-startRightMargin)*it).toInt()
            }
        }, 0.0f, 1.0f, duration, interpolator))
    }

    // 손이 등장하는 애니메이션을 시작한다.
    // 등장 이후에는 nextAnimator가 무한 반복된다.
    private fun startShowHandAnimationSet(imageView: ImageView, nextAnimator: AnimatorSet): AnimatorSet {
        val fromValue = 0.0f
        val toValue = 1.0f

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

    // 손이 사라지는 애니메이션을 시작한다.
    private fun startHideHandAnimationSet(imageView: ImageView): AnimatorSet {
        val fromValue = imageView.scaleX
        val toValue = 0.0f

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

    // 손으로 터치를 안내하는 애니메이션 세트
    fun touchGuideAnimatorSet(imageView: ImageView, duration: Long, toValue: Float): AnimatorSet {
        val fromValue = 1.0f

        val fromToSet = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(imageView, "scaleX", fromValue, toValue),
                ObjectAnimator.ofFloat(imageView, "scaleY", fromValue, toValue)
            )
        }
        val toFromSet = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(imageView, "scaleX", toValue, fromValue),
                ObjectAnimator.ofFloat(imageView, "scaleY", toValue, fromValue)
            )
        }

        val animatorSet = AnimatorSet().apply {
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator()
            playSequentially(
                fromToSet,
                toFromSet
            )
        }

        return animatorSet
    }

    // TODO(손으로 터치 후 드래그를 안내하는 애니메이션 세트)
    fun touchAndDragGuideAnimatorSet(): AnimatorSet {
        return AnimatorSet()
    }

    private fun draw() {
        // Clear Canvas
        canvas.drawRect(0.0f, 0.0f, binding.root.width.toFloat(), binding.root.height.toFloat(), clearPaint)

        // Cover
        canvas.drawRect(
            0.0f,
            0.0f,
            binding.root.width.toFloat(),
            binding.root.height.toFloat(),
            coverPaint
        )
        // Box
        canvas.drawRoundRect(
            boxLeft,
            boxTop,
            boxRight,
            boxBottom,
            boxCornerRadius,
            boxCornerRadius,
            boxPaint
        )
        // BoxStroke
        canvas.drawRoundRect(
            boxLeft-boxPadding,
            boxTop-boxPadding,
            boxRight+boxPadding,
            boxBottom+boxPadding,
            boxStrokeCornerRadius,
            boxStrokeCornerRadius,
            boxStrokePaint
        )

        // Arrow
        canvas.drawLine(arrowStartX, arrowStartY, arrowEndX, arrowEndY, arrowPaint)
        // ArrowEnd
        canvas.drawCircle(arrowEndX, arrowEndY, arrowEndSize, arrowPaint)

        // Apply Canvas
        if(::bitmap.isInitialized) {
            binding.canvas.setImageBitmap(bitmap)
        }
    }

    fun setDialogTitle(text: String, gravity: Int) {
        binding.dialogTitle.text = text
        binding.dialogTitle.gravity = gravity
    }
    fun showDialogTitle() {
        binding.dialogTitle.visibility = TextView.VISIBLE
        binding.dialogSeparator.visibility = TextView.VISIBLE
    }
    fun hideDialogTitle() {
        binding.dialogTitle.visibility = TextView.GONE
        binding.dialogSeparator.visibility = TextView.GONE
    }
    fun setDialogContent(text: String, gravity: Int, bolds: List<Pair<Int, Int>>) {
        binding.dialogContent.text = text
        binding.dialogContent.gravity = gravity

        val spannableString = SpannableString(text)
        for((start, end) in bolds) {
            spannableString.setSpan(StyleSpan(android.graphics.Typeface.BOLD), start, end, 0)
        }
        binding.dialogContent.text = spannableString
    }
    fun showDialog() {
        startObjectAnimatorOfDialog(
            toggleDuration,
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
    fun hideDialog() {
        startObjectAnimatorOfDialog(
            toggleDuration,
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
    fun showCover() {
        AnimUtils.startValueAnimatorOfFloat({
            coverPaint.alpha = (it*coverMaxAlpha).toInt()
            draw()
        }, 0.0f, 1.0f, toggleDuration)
    }
    fun hideCover() {
        AnimUtils.startValueAnimatorOfFloat({
            coverPaint.alpha = (it*coverMaxAlpha).toInt()
            draw()
        }, 1.0f, 0.0f, toggleDuration)
    }
    fun showBoxStroke() {
        AnimUtils.startValueAnimatorOfFloat({
            boxStrokePaint.alpha = (it*255).toInt()
            draw()
        }, 0.0f, 1.0f, toggleDuration)
    }
    fun hideBoxStroke() {
        AnimUtils.startValueAnimatorOfFloat({
            boxStrokePaint.alpha = (it*255).toInt()
            draw()
        }, 1.0f, 0.0f, toggleDuration)
    }
    fun showArrow() {
        AnimUtils.startValueAnimatorOfFloat({
            arrowPaint.alpha = (it*255).toInt()
            draw()
        }, 0.0f, 1.0f, toggleDuration)
    }
    fun hideArrow() {
        arrowPaint.alpha = 0

        /*AnimUtils.startValueAnimatorOfFloat({
            arrowPaint.alpha = (it*255).toInt()
            draw()
        }, 1.0f, 0.0f, showHideDuration)*/
    }

    // 단위는 dp이다.
    fun translateDialog(
        duration: Long,
        top: Float,
        bottom: Float,
        start: Float,
        end: Float
    ) {
        // dp -> px
        val top = AnimUtils.dpToPx(binding.root.context, top)
        val bottom = AnimUtils.dpToPx(binding.root.context, bottom)
        val start = AnimUtils.dpToPx(binding.root.context, start)
        val end = AnimUtils.dpToPx(binding.root.context, end)

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
        duration: Long,
        left: Float,
        top: Float,
        right: Float,
        bottom: Float
    ) {
        // dp -> px
        val left = AnimUtils.dpToPx(binding.root.context, left)
        val top = AnimUtils.dpToPx(binding.root.context, top)
        val right = AnimUtils.dpToPx(binding.root.context, right)
        val bottom = AnimUtils.dpToPx(binding.root.context, bottom)

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
        currentBoxStrokeTopY = top-boxPadding
        currentBoxStrokeBottomY = bottom+boxPadding
    }

    // 화살표의 시작점이 다이얼로그의 중앙을 향해 이동하도록 만든다.
    fun translateArrowStart(duration: Long) {
        val startArrowStartX = arrowStartX
        val startArrowStartY = arrowStartY

        registerAnimator("arrow_start", AnimUtils.startValueAnimatorOfFloat({
            arrowStartX = GeometryUtils.linear(startArrowStartX, currentDialogCenterX, it)
            arrowStartY = GeometryUtils.linear(startArrowStartY, currentDialogCenterY, it)
            draw()
        }, 0.0f, 1.0f, duration, AccelerateDecelerateInterpolator()))
    }

    // 화살표의 끝점이 다이얼로그의 중앙을 향해 이동하도록 만든다.
    fun translateArrowEndToDialog(duration: Long) {
        val startArrowEndX = arrowEndX
        val startArrowEndY = arrowEndY

        registerAnimator("arrow_end", AnimUtils.startValueAnimatorOfFloat({
            arrowEndX = GeometryUtils.linear(startArrowEndX, currentDialogCenterX, it)
            arrowEndY = GeometryUtils.linear(startArrowEndY, currentDialogCenterY, it)
            draw()
        }, 0.0f, 1.0f, duration, AccelerateDecelerateInterpolator()))
    }

    // 화살표의 끝점이 박스를 향해 이동하도록 만든다.
    fun translateArrowEndToBox(duration: Long) {
        val startArrowEndX = arrowEndX
        val startArrowEndY = arrowEndY

        registerAnimator("arrow_end", AnimUtils.startValueAnimatorOfFloat({
            arrowEndX = GeometryUtils.linear(startArrowEndX, currentBoxCenterX, it)
            arrowEndY = GeometryUtils.linear(
                startArrowEndY,
                if(currentDialogCenterY < currentBoxCenterY) currentBoxStrokeTopY else currentBoxStrokeBottomY,
                it
            )
            draw()
        }, 0.0f, 1.0f, duration, OvershootInterpolator(0.5f)))
    }

    // 오른쪽 아래에 그림자가 적용된 비트맵을 만든다.
    fun makeShadowedBitmap(source: Bitmap, x: Int, y: Int, color: Int): Bitmap {
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
        x: Float = 0.0f,
        y: Float = 0.0f,
        w: Float,
        h: Float,
        animator: Int
    ) {
        val imageView = ImageView(context)

        val bitmap = makeShadowedBitmap(
            ResourcesCompat.getDrawable(resources, source, null)!!.toBitmap(),
            12,
            12,
            Color.argb(99, 0, 0, 0)
        )
        imageView.setImageBitmap(bitmap)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER

        binding.gestureLayout.addView(imageView)

        imageView.rotation = 45.0f

        imageView.updateLayoutParams<ViewGroup.LayoutParams> {
            width = AnimUtils.dpToPx(binding.root.context, w).toInt()
            height = AnimUtils.dpToPx(binding.root.context, h).toInt()
        }

        hands[id] = Hand(
            imageView,
            startShowHandAnimationSet(imageView, touchGuideAnimatorSet(imageView, 1000, 1.2f))
        )
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
}

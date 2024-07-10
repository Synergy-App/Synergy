package com.sungkyul.synergy.learning_space.appinstall

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import com.sungkyul.synergy.R

class AppInstallLoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appinstall_loading)

        // 카카오 로고 이미지 서서히 줄어드는 애니메이션
        val kakaoAppImage = findViewById<ImageView>(R.id.kakao_app_image)
        val scaleDownAnimation = AnimationUtils.loadAnimation(this, R.anim.kakao_logo_scale_down)
        kakaoAppImage.startAnimation(scaleDownAnimation)

        // 스피너 추가
        val spinner = findViewById<ProgressBar>(R.id.image_loading_spinner)
        spinner.visibility = View.VISIBLE
        // 스피너 색상 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            spinner.indeterminateDrawable.setColorFilter(
                Color.parseColor("#0B57CF"), PorterDuff.Mode.SRC_IN
            )
        } else {
            val wrapDrawable = DrawableCompat.wrap(spinner.indeterminateDrawable)
            DrawableCompat.setTint(wrapDrawable, Color.parseColor("#0B57CF"))
            spinner.indeterminateDrawable = DrawableCompat.unwrap(wrapDrawable)
        }

        // 2.5초 후에 스피너와 after_kakao_app_image를 지우는 작업 예약
        Handler().postDelayed({
            spinner.visibility = View.GONE
            findViewById<ImageView>(R.id.after_kakao_app_image).visibility = View.VISIBLE
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 4%"
        }, 2500)


        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).setImageResource(R.drawable.ic_install_kakao_down_line2)
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 17%"
        }, 3500)

        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).setImageResource(R.drawable.ic_install_kakao_down_line3)
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 30%"
        }, 4500)

        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).setImageResource(R.drawable.ic_install_kakao_down_line4)
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 49%"
        }, 5500)

        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).setImageResource(R.drawable.ic_install_kakao_down_line5)
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 77%"
        }, 6500)

        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).setImageResource(R.drawable.ic_install_kakao_down_line6)
            findViewById<TextView>(R.id.kakao_info_text).text = "89.89MB 중 100%"
        }, 7500)

        // 스피너를 보이게 하는 시점에만 색상 변경
        // 8.5초 후에 after_kakao_app_image를 안보이게 하고 스피너를 보이게 함
        Handler().postDelayed({
            findViewById<ImageView>(R.id.after_kakao_app_image).visibility = View.GONE
            findViewById<TextView>(R.id.kakao_info_text).text = "설치 중..."
            spinner.visibility = View.VISIBLE
            // 스피너 색상 변경
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                spinner.indeterminateDrawable.setColorFilter(
                    Color.parseColor("#C6C6C6"), PorterDuff.Mode.SRC_IN
                )
            } else {
                val wrapDrawable = DrawableCompat.wrap(spinner.indeterminateDrawable)
                DrawableCompat.setTint(wrapDrawable, Color.parseColor("#C6C6C6"))
                spinner.indeterminateDrawable = DrawableCompat.unwrap(wrapDrawable)
            }
        }, 8500)

        // 11초 후에 after_kakao_app_image의 크기를 원본 사이즈로 확대하는 애니메이션
        Handler().postDelayed({
            val scaleUpAnimation = AnimationUtils.loadAnimation(this, R.anim.kakao_logo_scale_up)
            findViewById<ImageView>(R.id.kakao_app_image).startAnimation(scaleUpAnimation)
            findViewById<TextView>(R.id.kakao_info_text).text = "설치됨"
            spinner.visibility = View.GONE
            findViewById<Button>(R.id.kakao_cancel_btn).visibility = View.GONE
            findViewById<Button>(R.id.after_kakao_install_btn).visibility = View.VISIBLE
        }, 11000)


    }
}

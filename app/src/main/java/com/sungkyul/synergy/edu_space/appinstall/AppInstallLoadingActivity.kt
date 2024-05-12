package com.sungkyul.synergy.edu_space.appinstall

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
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

        // 2초 후에 다음 액티비티로 이동
        Handler().postDelayed({
            val intent = Intent(this, AppInstallLoading2Activity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(0, 0) // 애니메이션 없이 전환
        }, 2500)

    }
}

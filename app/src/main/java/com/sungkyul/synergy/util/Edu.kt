package com.sungkyul.synergy.util

import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sungkyul.synergy.R

interface EduData

// 교육에서 설명을 담당하는 클래스
data class EduDescription(
    val title: String,
    val content: String,
    val duration: Int,
    val dialogTop: Int,
    val dialogBottom: Int,
    val dialogStart: Int,
    val dialogEnd: Int,
    val highlight: Boolean,
    val topCoverSize: Int,
    val bottomCoverSize: Int,
    val startCoverSize: Int,
    val endCoverSize: Int
): EduData

data class EduImgDescription(
    val title: String,
    val content: String,
    val duration: Int,
    val src: String,
    val dialogTop: Int,
    val dialogBottom: Int,
    val dialogStart: Int,
    val dialogEnd: Int
): EduData

// 교육에서 연습을 담당하는 클래스
data class EduPractice(
    val id: String = "",
    val fingers: ArrayList<Int>
): EduData

/*
# 교육 내용을 편하게 제작할 수 있도록 만든 클래스

## 사용법
교육을 진행할 액티비티가 `Name`이라 가정한다.

1. `activity_<name>.xml`의 루트 레이아웃 안에 교육 화면을 보여줄 FrameLayout을 추가한다.
```
<FrameLayout
    android:id="@+id/edu_screen"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

2. `<Name>Activity.kt` 클래스의 `onCreate()` 함수 안에 Edu 클래스를 선언한다.
```
// 교육 과정을 만든다.
val course = ArrayList<EduData>()
course.add(...)
course.add(...)
...

// 교육 클래스를 선언한다.
val edu = Edu(this, course)

// 교육을 시작한다.
edu.start()
```

## 교육 과정 만들기
교육 과정은 다음과 같이 만들 수 있고, 오름차순으로 진행한다.
```
val course = ArrayList<EduData>()
```

### 설명 추가
```
course.add(EduDescription(

))
```

### 이미지가 들어간 설명 추가
```
course.add(EduImgDescription(

))
```

### 연습 추가
```
course.add(EduPractice(

))
```

*/
class Edu(
    private val activity: AppCompatActivity,
    private val course: ArrayList<EduData>
) {
    private var num = 0
    private val eduScreenFragment = EduScreenFragment()
    private val eduScreen = activity.findViewById<FrameLayout>(R.id.edu_screen)

    private val onEduScreenClickListener = { _: View -> next() }

    fun start() {
        // 교육 화면을 맨 앞으로 오게 한다.
        eduScreen.bringToFront()

        replaceFragment(activity.supportFragmentManager, R.id.edu_screen, eduScreenFragment)

        num = 0

        // 프래그먼트 화면의 클릭 이벤트 설정
        eduScreen.setOnClickListener(onEduScreenClickListener)
    }

    fun action(id: String) {
        val eduData = course[num] as EduPractice
        if(id == eduData.id) {
            next()
        }
    }

    private fun next() {
        num++

        Log.i("size", "${AnimUtil.getScreenDimensions(eduScreen.context).first}")

        eduScreenFragment.translateCovers(
            1000,
            0,
            10,
            0,
            100,
            0,
            10,
            0,
            100
        )

        /*eduScreenFragment.hideDialog()
        eduScreenFragment.hideCovers()
        eduScreen.isClickable = false*/

        /*if(num >= course.size) {

        } else if(course[num] is EduDescription) {

        }*/
    }

    private fun replaceFragment(fragmentManager: FragmentManager, screenID: Int, fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(screenID, fragment)
            .commit()
    }
}

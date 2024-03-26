package com.sungkyul.synergy.learning_space.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungkyul.synergy.backend.BackendTest
import com.sungkyul.synergy.backend.QuizInfo
import com.sungkyul.synergy.backend.questionAPI
import com.sungkyul.synergy.databinding.ActivityLearningIconBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**아이콘 실습 버튼 시작 클릭시 화면 전환 하는 화면
 * 기본교육으로 감
 * */

class LearningDefaultAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearningIconBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearningIconBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.iconStartBtn.setOnClickListener {
            val intent = Intent(this, BackendTest::class.java)
            startActivity(intent)
        }
        /**백엔드 연결 부분 */
        // 레트로핏 인스턴스 생성하기
        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.16.111.213/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val quizApi = retrofit.create(questionAPI::class.java)

        quizApi.getLearningSpaceInfo().enqueue(object : Callback<List<QuizInfo>> {
            override fun onResponse(
                call: Call<List<QuizInfo>>,
                response: Response<List<QuizInfo>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val learningInfo = response.body()!!
                    if (learningInfo.isNotEmpty()) {
                        val firstLearningInfo = learningInfo[2]
                        with(binding) {
                            learningInfoTv.text = firstLearningInfo.learningInfo
                            learningInfoTv2.text = firstLearningInfo.learningInfo
                            examNumberTv.text = firstLearningInfo.option1
                            levelNumberTv.text = firstLearningInfo.option2
                        }
                    } else {
                        // learningInfo가 비어있을 때 처리
                    }
                } else {
                    // 응답이 실패했을 때 처리
                }
            }

            override fun onFailure(call: Call<List<QuizInfo>>, t: Throwable) {
                // 오류 처리
            }
        })
    }
}
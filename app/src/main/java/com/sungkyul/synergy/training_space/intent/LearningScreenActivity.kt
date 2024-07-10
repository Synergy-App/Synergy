package com.sungkyul.synergy.training_space.intent

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.databinding.FragmentLearningIconBinding
import com.sungkyul.synergy.training_space.NewScreenPracticeActivity

/**아이콘 실습 버튼 시작 클릭시 화면 전환 하는 화면
 * 화면구성 교육으로 감
 * */

class LearningScreenFragment : Fragment() {
    private var _binding: FragmentLearningIconBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLearningIconBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.iconStartBtn.setOnClickListener {
            val intent = Intent(requireContext(), NewScreenPracticeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


        /**백엔드 연결 부분 */
        // 레트로핏 인스턴스 생성하기
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://172.16.111.213/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val quizApi = retrofit.create(questionAPI::class.java)
//
//        quizApi.getLearningSpaceInfo().enqueue(object : Callback<List<QuizInfo>> {
//            override fun onResponse(
//                call: Call<List<QuizInfo>>,
//                response: Response<List<QuizInfo>>
//            ) {
//                if (response.isSuccessful && response.body() != null) {
//                    val learningInfo = response.body()!!
//                    if (learningInfo.isNotEmpty()) {
//                        val firstLearningInfo = learningInfo[1]
//                        with(binding) {
//                            learningInfoTv.text = firstLearningInfo.learningInfo
////                            learningInfoTv2.text = firstLearningInfo.learningInfo
////                            examNumberTv.text = firstLearningInfo.option1
////                            levelNumberTv.text = firstLearningInfo.option2
//                        }
//                    } else {
//                        // learningInfo가 비어있을 때 처리
//                    }
//                } else {
//                    // 응답이 실패했을 때 처리
//                }
//            }
//
//            override fun onFailure(call: Call<List<QuizInfo>>, t: Throwable) {
//                // 오류 처리
//            }
//        }
//        )

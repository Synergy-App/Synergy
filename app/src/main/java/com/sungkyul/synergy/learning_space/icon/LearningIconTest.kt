package com.sungkyul.synergy.learning_space.icon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.sungkyul.synergy.R
import com.sungkyul.synergy.learning_space.icon.data.QuizData

class LearningIconTest : AppCompatActivity() {

    // Firebase Realtime Database 참조 가져오기
    val database = FirebaseDatabase.getInstance()
    val quizReference = database.getReference("quizzes")

    // 퀴즈 데이터 리스트
    val quizzes = listOf(
        QuizData("다음은 어떤 아이콘인가요?", listOf("블루투스", "블루장생", "전화버튼", "와이파이"), "와이파이"),
        QuizData("다음은 어떤 아이콘인가요?", listOf("블루투스", "블루장생", "전화버튼", "와이파이"), "전화버튼"),
    )

    var currentQuizIndex = 0 // 현재 퀴즈 인덱스
    var currentQuiz: QuizData? = null // 현재 퀴즈 데이터
    private var lastClickedCardView: CardView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_icon_test)

        // 초기 퀴즈 데이터로 UI 업데이트
        updateUIWithCurrentQuiz()

        // "다음으로" 버튼 클릭 시 다음 퀴즈로 이동

        // "다음으로" 버튼 클릭 리스너 설정
        val nextButton: CardView = findViewById(R.id.next_btn)
        nextButton.setOnClickListener {
            onNextButtonClicked()
        }

        // 각 옵션 텍스트뷰에 클릭 리스너 설정
        val optionTextView1: TextView = findViewById(R.id.option_TextView1)
        val optionTextView2: TextView = findViewById(R.id.option_TextView2)
        val optionTextView3: TextView = findViewById(R.id.option_TextView3)
        val optionTextView4: TextView = findViewById(R.id.option_TextView4)
        val cardView1: CardView = findViewById(R.id.cardView1)
        val cardView2: CardView = findViewById(R.id.cardView2)
        val cardView3: CardView = findViewById(R.id.cardView3)
        val cardView4: CardView = findViewById(R.id.cardView4)

        optionTextView1.setOnClickListener {
            onOptionSelected(currentQuiz?.options?.get(0))
        }

        optionTextView2.setOnClickListener {
            onOptionSelected(currentQuiz?.options?.get(1))
        }

        optionTextView3.setOnClickListener {
            onOptionSelected(currentQuiz?.options?.get(2))
        }

        optionTextView4.setOnClickListener {
            onOptionSelected(currentQuiz?.options?.get(3))
        }
        cardView1.setOnClickListener {
            onCardViewClicked(cardView1)
        }

        cardView2.setOnClickListener {
            onCardViewClicked(cardView2)
        }
        cardView3.setOnClickListener {
            onCardViewClicked(cardView3)
        }

        cardView4.setOnClickListener {
            onCardViewClicked(cardView4)
        }
    }

    // 현재 퀴즈 데이터로 UI 업데이트
    private fun updateUIWithCurrentQuiz() {
        val currentQuiz = quizzes[currentQuizIndex]

        // 문제와 이미지뷰 등을 업데이트
        val questionTextView: TextView = findViewById(R.id.question_TextView)
        questionTextView.text = currentQuiz.question

        val iconImageView: ImageView = findViewById(R.id.icon_ImageView)

        // Glide를 사용하여 현재 퀴즈에 해당하는 이미지 설정
        Glide.with(this)
            .load(getImageResource(currentQuiz))
            .into(iconImageView)

        // 선택지 카드뷰 업데이트
        updateOptionCardView(currentQuiz.options[0], R.id.cardView1, R.id.option_TextView1)
        updateOptionCardView(currentQuiz.options[1], R.id.cardView2, R.id.option_TextView2)
        updateOptionCardView(currentQuiz.options[2], R.id.cardView3, R.id.option_TextView3)
        updateOptionCardView(currentQuiz.options[3], R.id.cardView4, R.id.option_TextView4)

        // 선택된 옵션 초기화
        lastClickedCardView?.setCardBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.icon_learning_btn
            )
        )
        lastClickedCardView = null
    }

    // 현재 퀴즈에 따라 다른 이미지 리소스를 가져오는 함수
    private fun getImageResource(quiz: QuizData): Int {
        return when (quiz.question) {
            "다음은 어떤 아이콘인가요?" -> {
                if (quiz.correctAnswer == "와이파이") {
                    R.drawable.wifi
                } else {
                    R.drawable.ic_call_green_24
                }
            }

            else -> R.drawable.google_lens
        }
    }


    // 선택지 카드뷰 업데이트
    private fun updateOptionCardView(option: String?, cardViewId: Int, optionTextViewId: Int) {
        val cardView: CardView = findViewById(cardViewId)
        val optionTextView: TextView = cardView.findViewById(optionTextViewId)
        optionTextView.text = option
    }

    // 카드뷰 클릭 시 호출되는 메소드
    private fun onCardViewClicked(clickedCardView: CardView) {
        // 이전에 클릭된 카드뷰의 배경색 초기화
        lastClickedCardView?.setCardBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.icon_learning_btn
            )
        )

        // 현재 클릭된 카드뷰의 배경색 변경
        clickedCardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.naver))

        // 현재 클릭된 카드뷰를 저장
        lastClickedCardView = clickedCardView

    }

    // 옵션 텍스트 클릭 시 호출되는 메소드
    private fun onOptionSelected(selectedOption: String?) {
        val correctAnswer = currentQuiz?.correctAnswer

        if (selectedOption == correctAnswer) {
            // 정답일 경우
            // 여기에서 원하는 동작을 수행하세요.
            // 예를 들어, 정답 메시지를 표시하거나 점수를 증가시키는 등의 동작을 추가할 수 있습니다.
        } else {
            // 오답일 경우
            // 여기에서 원하는 동작을 수행하세요.
            // 예를 들어, 오답 메시지를 표시하거나 재시도 기회를 제공하는 등의 동작을 추가할 수 있습니다.
        }
    }

    // "다음으로" 버튼 클릭 시 호출되는 메소드
    private fun onNextButtonClicked() {
        // 정답 여부를 확인하고 처리
        val selectedOption =
            lastClickedCardView?.findViewById<TextView>(R.id.option_TextView1)?.text.toString()
        val correctAnswer = currentQuiz?.correctAnswer

        if (selectedOption == correctAnswer) {
            // 정답일 경우
            // 여기에서 원하는 동작을 수행하세요.
            // 예를 들어, 정답 메시지를 표시하거나 점수를 증가시키는 등의 동작을 추가할 수 있습니다.
        } else {
            // 오답일 경우
            // 여기에서 원하는 동작을 수행하세요.
            // 예를 들어, 오답 메시지를 표시하거나 재시도 기회를 제공하는 등의 동작을 추가할 수 있습니다.
        }
        // Firebase에 데이터 저장
        val currentQuizReference = quizReference.child("quiz_$currentQuizIndex")
        currentQuizReference.child("question").setValue(currentQuiz?.question)
       // currentQuizReference.child("options").setValue(currentQuiz?.options)
        currentQuizReference.child("correctAnswer").setValue(currentQuiz?.correctAnswer)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Firebase에 데이터가 성공적으로 저장됨
                    Log.d("Firebase", "Firebase에 데이터가 성공적으로 저장됨")

                    // TODO: 이후에 실행할 코드 추가
                    // 예를 들어, UI 업데이트 또는 다음 작업 호출 등을 추가할 수 있습니다.

                    // 퀴즈 인덱스 증가 및 UI 업데이트
                    currentQuizIndex++
                    if (currentQuizIndex < quizzes.size) {
                        // 퀴즈 데이터가 남아있으면 UI 업데이트
                        updateUIWithCurrentQuiz()
                    } else {
                        // 모든 퀴즈가 끝났을 때의 처리
                        // 예를 들어, 다음 화면으로 이동하거나 결과를 표시할 수 있음
                    }
                } else {
                    // 저장 중에 오류 발생
                    Log.e("Firebase", "Firebase 데이터 저장 중 오류 발생: ${task.exception?.message}")
                }
            }

    }
}

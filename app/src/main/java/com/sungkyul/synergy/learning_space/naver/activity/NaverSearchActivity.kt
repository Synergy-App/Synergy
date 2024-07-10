package com.sungkyul.synergy.learning_space.naver.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.databinding.ActivityNaverSearchBinding
import com.sungkyul.synergy.learning_space.naver.adapter.NaverAutocompleteAdapter
import com.sungkyul.synergy.learning_space.naver.adapter.NaverAutocompleteData
import com.sungkyul.synergy.utils.TextUtils

class NaverSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaverSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaverSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val naverAutocompleteArray = ArrayList<NaverAutocompleteData>()

        val naverAutocompleteList = binding.naverAutocompleteList
        naverAutocompleteList.layoutManager = LinearLayoutManager(binding.root.context)
        naverAutocompleteList.adapter = NaverAutocompleteAdapter(naverAutocompleteArray, this)

        binding.searchEditText.requestFocus()

        binding.searchEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val adapter = naverAutocompleteList.adapter as NaverAutocompleteAdapter

                // 모든 아이템들을 삭제한다.
                adapter.notifyItemRangeRemoved(0, naverAutocompleteArray.size)

                // 자동 완성
                naverAutocompleteArray.clear()
                if(s.toString().isNotEmpty()) {
                    for (i in TextUtils.searchAll(s.toString())) {
                        naverAutocompleteArray.add(NaverAutocompleteData(i))
                    }
                }

                // 아이템들을 추가한다.
                adapter.notifyItemRangeInserted(0, naverAutocompleteArray.size)
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.previousButton.setOnClickListener {
            finish()
        }
        binding.clearButton.setOnClickListener {
            binding.searchEditText.setText("")
        }
        binding.searchButton.setOnClickListener {
            val searchQuery = binding.searchEditText.text.toString()
            if (searchQuery == "된장찌개 만드는 법") {
                val intent = Intent(this, NaverSearchInfoActivity::class.java)
                intent.putExtra("search_query", searchQuery)
                startActivity(intent)
            } else {
                // 기존의 검색 결과를 표시하는 코드
                val intent = Intent(this, NaverSearchResultActivity::class.java)
                intent.putExtra("search_words", searchQuery)
                startActivity(intent)
            }
        }
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}

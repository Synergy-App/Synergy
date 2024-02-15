package com.sungkyul.synergy.edu_space.default_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.ActivityNaverBinding
import com.sungkyul.synergy.databinding.ActivityNaverSearchBinding

class NaverSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaverSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaverSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val naverAutocompleteArray = ArrayList<NaverAutocompleteData>()
        naverAutocompleteArray.add(NaverAutocompleteData("아"))
        naverAutocompleteArray.add(NaverAutocompleteData("야"))
        naverAutocompleteArray.add(NaverAutocompleteData("어"))
        naverAutocompleteArray.add(NaverAutocompleteData("여"))
        naverAutocompleteArray.add(NaverAutocompleteData("오"))
        naverAutocompleteArray.add(NaverAutocompleteData("요"))
        naverAutocompleteArray.add(NaverAutocompleteData("우"))
        naverAutocompleteArray.add(NaverAutocompleteData("유"))
        naverAutocompleteArray.add(NaverAutocompleteData("으"))
        naverAutocompleteArray.add(NaverAutocompleteData("이"))

        val naverAutocompleteList = binding.naverAutocompleteList
        naverAutocompleteList.layoutManager = LinearLayoutManager(binding.root.context)
        naverAutocompleteList.adapter = NaverAutocompleteAdapter(naverAutocompleteArray)
    }
}

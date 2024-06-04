package com.sungkyul.synergy.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.R
import com.sungkyul.synergy.RegisterActivity

class FragmentRegisterId : Fragment() {

    private lateinit var editTextId: EditText
    private lateinit var btnNext: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_id, container, false)

        editTextId = view.findViewById(R.id.editTextId)
        btnNext = view.findViewById(R.id.btnNext_Id)

        editTextId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // No need to implement
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No need to implement
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    btnNext.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                } else {
                    btnNext.setBackgroundColor(resources.getColor(android.R.color.holo_orange_light))
                }
            }
        })

        btnNext.setOnClickListener {
            val userId = editTextId.text.toString().trim()
            if (userId.isNotEmpty()) {
                (activity as RegisterActivity).navigateToPasswordFragment(userId)
            }
        }

        return view
    }
}

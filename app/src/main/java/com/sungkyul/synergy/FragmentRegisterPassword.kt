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

class FragmentRegisterPassword : Fragment() {

    private lateinit var editTextPassword: EditText
    private lateinit var btnNext: Button

    companion object {
        private const val ARG_USER_ID = "user_id"

        fun newInstance(userId: String): FragmentRegisterPassword {
            val fragment = FragmentRegisterPassword()
            val args = Bundle()
            args.putString(ARG_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_password, container, false)

        editTextPassword = view.findViewById(R.id.editTextPassword)
        btnNext = view.findViewById(R.id.btnNext_Password)

        editTextPassword.addTextChangedListener(object : TextWatcher {
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
            val password = editTextPassword.text.toString().trim()
            val userId = arguments?.getString(ARG_USER_ID) ?: return@setOnClickListener
            if (password.isNotEmpty()) {
                (activity as RegisterActivity).navigateToNicknameFragment(userId, password)
            }
        }

        return view
    }
}

package com.sungkyul.synergy.utils

import android.text.Editable
import android.text.TextWatcher

abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // 기본 구현 - 필요시 오버라이드 하여 사용
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // 기본 구현 - 필요시 오버라이드 하여 사용
    }

    override fun afterTextChanged(s: Editable?) {
        // 기본 구현 - 필요시 오버라이드 하여 사용
    }
}

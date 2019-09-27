package com.kingtech.unittest.common

import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern

/**
 * An Email format validator for {@link android.widget.EditText}.
 */


object EmailValidator : TextWatcher {
	var EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." +
			"[a-zA-Z0-9_+&*-]+)*@" +
			"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
			"A-Z]{2,7}$"
	
	private var isValid = false
	
	fun isValid(): Boolean {
		return isValid
	}
	
	fun isEmailValid(email: String): Boolean {
		val pattern = Pattern.compile(EMAIL_REGEX)
		return email.isNotEmpty() && pattern.matcher(email).matches()
	}
	
	override fun afterTextChanged(editable: Editable) {
		//isValid = isEmailValid(editable)
	}
	
	override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
	}
	
	override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
	
	}
	
}
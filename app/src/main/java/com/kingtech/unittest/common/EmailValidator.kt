package com.kingtech.unittest.common

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import java.util.regex.Pattern

/**
 * An Email format validator for {@link android.widget.EditText}.
 */


object EmailValidator : TextWatcher {
	
	private val EMAIL_PATTERN: Pattern = Pattern.compile(
		"[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
				"\\@" +
				"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
				"(" +
				"\\." +
				"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
				")+"
	)
	
	private var isValid = false
	
	fun isValid(): Boolean {
		return isValid
	}
	
	fun isEmailValid(email: String): Boolean {
		return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
	}
	
	override fun afterTextChanged(editable: Editable) {
		//isValid = isEmailValid(editable)
	}
	
	override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
	}
	
	override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
	
	}
	
}
package com.kingtech.unittest

import android.content.SharedPreferences
import com.kingtech.unittest.data.User
import java.util.*

class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {
	
	companion object {
		// Keys for saving values in SharedPreferences.
		const val NAME = "name"
		const val DOB = "date_of_birth"
		const val EMAIL = "email"
		const val SHARED_PREF = "Shared_pref"
	}
	
	private val editor = sharedPreferences.edit()
	
	/*There is a better way of saving a user object to our SharedPreferences
	* using the Gson Library from Google*/
	fun saveUserInfo(user: User): Boolean {
		editor.putString(NAME, user.name)
		editor.putLong(DOB, user.dob.timeInMillis)
		editor.putString(EMAIL, user.email)
		return editor.commit()
	}
	
	fun getSavedUserInfo(): User? {
		// Get data from the SharedPreferences.
		val name = sharedPreferences.getString(NAME, "")
		val dob = sharedPreferences.getLong(DOB, Calendar.getInstance().timeInMillis)
		val dateOfBirth = Calendar.getInstance()
		dateOfBirth.timeInMillis = dob
		val email = sharedPreferences.getString(EMAIL, "")
		return User(name, email, dateOfBirth)
	}
	
	fun clear() {
		editor.clear().apply()
	}
	
}

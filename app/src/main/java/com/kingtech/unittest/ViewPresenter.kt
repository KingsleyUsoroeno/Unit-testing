package com.kingtech.unittest

import android.content.SharedPreferences
import com.kingtech.unittest.data.User

interface ViewPresenter {
	
	fun revertClick()
	
	fun showMsg(msg: String)
	
	fun updateView(user: User)
	
	fun getSharedPreferences(): SharedPreferences
	
	fun getUser(): User
	
	fun showErrorMsg(msg: String)
}
package com.kingtech.unittest

import com.kingtech.unittest.common.EmailValidator

class ViewContract(private val viewPresenter: ViewPresenter) {
	
	private val sharedPreferences = viewPresenter.getSharedPreferences()
	
	private val sharedPreferencesHelper = SharedPreferencesHelper(sharedPreferences)
	
	fun saveUser() {
		val user = viewPresenter.getUser()
		if (user.email!!.isEmpty()) {
			viewPresenter.showErrorMsg("Email Field is Required")
			
		} else {
			if (!EmailValidator.isEmailValid(user.email!!)) {
				viewPresenter.showErrorMsg("Not a valid Email")
				return
			}
		}
		
		if (user.name!!.isEmpty()) {
			viewPresenter.showErrorMsg("User Name is Required")
			return
		}
		
		val isSuccessful = sharedPreferencesHelper.saveUserInfo(user)
		if (isSuccessful) viewPresenter.showMsg("info saved") else viewPresenter.showMsg("Failed to save")
		updateUi()
		
		/*
		* For Test
		* Step 1 Create a User Object that would make our test fail
		* Step 2 Create a User Object that would make our test pass */
	}
	
	fun deleteUser() {
		sharedPreferencesHelper.clear()
		viewPresenter.revertClick()
	}
	
	fun updateUi() {
		val user = sharedPreferencesHelper.getSavedUserInfo()
		if (user != null) {
			viewPresenter.updateView(user)
		}
	}
}
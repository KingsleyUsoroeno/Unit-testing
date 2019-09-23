package com.kingtech.unittest

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.kingtech.unittest.SharedPreferencesHelper.Companion.SHARED_PREF
import com.kingtech.unittest.common.EmailValidator
import com.kingtech.unittest.data.User
import java.util.*


class MainActivity : AppCompatActivity(), ViewPresenter {
	// Logger for this class.
	private val TAG = "MainActivity"
	/**
	 * An {@link MainActivity} that represents an input form page where the User can provide his name, date
	 * of birth and email address. The personal information can be saved to {@link SharedPreferences}
	 * by clicking a button.
	 */
	
	private lateinit var nameEdt: TextInputEditText
	private lateinit var emailEdt: TextInputEditText
	private lateinit var datePicker: DatePicker
	private lateinit var emailValidator: EmailValidator
	private lateinit var viewContract: ViewContract
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		viewContract = ViewContract(this)
		initViews()
	}
	
	private fun initViews() {
		nameEdt = findViewById(R.id.text_input_name)
		emailEdt = findViewById(R.id.text_input_email)
		//emailEdt.addTextChangedListener(emailValidator)
		datePicker = findViewById(R.id.dateOfBirthInput)
		viewContract.updateUi()
	}
	
	override fun getSharedPreferences(): SharedPreferences {
		return getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
	}
	
	override fun revertClick() {
		nameEdt.setText("")
		emailEdt.setText("")
	}
	
	override fun updateView(user: User) {
		nameEdt.setText(user.name)
		emailEdt.setText(user.email)
		val dob = user.dob
		datePicker.init(
			dob.get(Calendar.YEAR), dob.get(Calendar.MONTH),
			dob.get(Calendar.DAY_OF_MONTH), null
		)
	}
	
	override fun getUser(): User {
		// Get the text from the input fields.
		val name = nameEdt.text.toString()
		val dateOfBirth = Calendar.getInstance()
		dateOfBirth.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
		val email = emailEdt.text.toString()
		return User(name, email, dateOfBirth)
	}
	
	override fun showErrorMsg(msg: String) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
	}
	
	override fun showMsg(msg: String) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
	}
	
	fun onSaveClick(view: View) {
		viewContract.saveUser()
	}
	
	fun onRevertClick(view: View) {
		viewContract.deleteUser()
	}
}

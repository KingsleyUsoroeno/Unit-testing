package com.kingtech.unittest

import com.kingtech.unittest.common.EmailValidator
import com.kingtech.unittest.data.User
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class ViewContractTest {
	
	private lateinit var correctUser: User
	
	private lateinit var wrongUser: User
	
	
	@Before
	fun setUp() {
		correctUser = User("Kingsley", "kingsley@gmail.com", Calendar.getInstance())
		wrongUser = User("", "", Calendar.getInstance())
	}
	
	@Test
	fun should_return_a_correct_user_email() {
		assertNotNull(correctUser.email)
	}
	
	@Test
	fun should_return_a_valid_email() {
		assertTrue(EmailValidator.isEmailValid(correctUser.email!!))
	}
	
	@Test
	fun should_return_a_correct_user_name() {
		assertNotNull(correctUser.email)
	}
	
	// Make test name more meaningful
//	@Test
//	fun should_return_a_wrong_user_name(){
//		assertEquals("",)
//	}
//
//	@Test
//	fun should_return_an_wrong_user_email(){
//		assertNull(wrongUser.email)
//	}
	
	@Test
	fun should_return_an_invalid_email() {
		assertFalse(EmailValidator.isEmailValid(wrongUser.email!!))
	}
}
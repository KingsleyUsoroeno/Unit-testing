package com.kingtech.unittest

import com.kingtech.unittest.common.EmailValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for the EmailValidator logic.
 */
class EmailValidatorTest {
	
	private lateinit var emailValidator: EmailValidator
	
	@Before
	fun initReferences() {
		emailValidator = EmailValidator()
	}
	
	
	@Test
	fun correctEmail() {
		assertTrue(emailValidator.isEmailValid("name@email.com"))
	}
	
	@Test
	fun correctEmailWithSubDomain() {
		assertTrue(emailValidator.isEmailValid("name@email.co.uk"))
	}
	
	@Test
	fun isInvalidEmail() {
		assertFalse(emailValidator.isEmailValid("name@email"))
	}
	
	@Test
	fun isEmailDoubleDotted() {
		assertFalse(emailValidator.isEmailValid("name@email..com"))
	}
	
	@Test
	fun isEmailWithoutUserName() {
		assertFalse(emailValidator.isEmailValid("@email.com"))
	}
	
	@Test
	fun noEmailProvided() {
		assertFalse(emailValidator.isEmailValid(""))
	}
	
	
}
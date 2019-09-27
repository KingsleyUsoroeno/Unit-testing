package com.kingtech.unittest

import com.kingtech.unittest.common.EmailValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for the EmailValidator logic.
 */
class EmailValidatorTest {
	
	@Test
	fun correctEmail() {
		assertTrue(EmailValidator.isEmailValid("name@email.com"))
	}
	
	@Test
	fun correctEmailWithSubDomain() {
		assertTrue(EmailValidator.isEmailValid("name@email.co.uk"))
	}
	
	@Test
	fun isInvalidEmail() {
		assertFalse(EmailValidator.isEmailValid("name@email"))
	}
	
	@Test
	fun isEmailDoubleDotted() {
		assertFalse(EmailValidator.isEmailValid("name@email..com"))
	}
	
	@Test
	fun isEmailWithoutUserName() {
		assertFalse(EmailValidator.isEmailValid("@email.com"))
	}
	
	@Test
	fun noEmailProvided() {
		assertFalse(EmailValidator.isEmailValid(""))
	}
}
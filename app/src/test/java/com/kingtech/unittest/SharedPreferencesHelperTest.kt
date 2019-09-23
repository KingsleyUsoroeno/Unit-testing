package com.kingtech.unittest

import android.content.SharedPreferences
import com.kingtech.unittest.data.User
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.lenient
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Mockito is a Java library that is used for testing java applications
 * preferably testing java classes, It is used to mock the interfaces so that dummy objects can be
 * created and used to provide the dependencies for the class being tested.
 */

/**
 * Unit tests for the {@link SharedPreferencesHelper} that mocks {@link SharedPreferences}.
 */
@RunWith(MockitoJUnitRunner::class)
class SharedPreferencesHelperTest {
	
	companion object {
		
		private const val TEST_NAME = "Test name"
		
		private const val TEST_EMAIL = "test@email.com"
		
		private val TEST_DATE_OF_BIRTH = Calendar.getInstance()
	}
	
	lateinit var user: User
	
	private lateinit var mockSharedPreferenceHelper: SharedPreferencesHelper
	
	private lateinit var mMockBrokenSharedPreferencesHelper: SharedPreferencesHelper
	
	@Mock
	lateinit var mMockSharedPreferences: SharedPreferences
	
	@Mock
	lateinit var mMockBrokenSharedPreferences: SharedPreferences
	
	@Mock
	lateinit var mMockEditor: SharedPreferences.Editor
	
	@Mock
	lateinit var mMockBrokenEditor: SharedPreferences.Editor
	
	
	@Before
	fun initPreferences() {
		TEST_DATE_OF_BIRTH.set(1992, 2, 23)
		user = User(TEST_NAME, TEST_EMAIL, TEST_DATE_OF_BIRTH)
		
		// Create a mocked SharedPreferencesHelper that Saves Data to our SharedPreferences Successfully.
		mockSharedPreferenceHelper = createMockSharedPreference()
		
		// Create a mocked SharedPreferences that fails at saving data to our SharedPreferences.
		mMockBrokenSharedPreferencesHelper = createBrokenMockSharedPreference()
	}
	
	@Test
	fun sharedPreferencesHelper_SaveAndReadPersonalInformation() {
		// Save the personal information to SharedPreferences
		val success = mockSharedPreferenceHelper.saveUserInfo(user)
		assertThat(
			"Checking that SharedPreferenceEntry.save... returns true",
			success, `is`(true)
		)
		val currentUser = mockSharedPreferenceHelper.getSavedUserInfo()
		assertThat(
			"Checking that UserName has been persisted and read correctly",
			user.name, `is`(equalTo(currentUser?.name))
		)
		assertThat(
			"Checking that SharedPreferenceEntry.email has been persisted and read "
					+ "correctly", user.email, `is`(equalTo(currentUser?.email))
		)
		
		assertThat(
			"Checking that SharedPreferenceEntry.dateOfBirth has been persisted and read " + "correctly",
			user.dob,
			`is`(equalTo(currentUser?.dob))
		)
	}
	
	/**
	 * Creates a mocked SharedPreferences.
	 */
	private fun createMockSharedPreference(): SharedPreferencesHelper {
		// Mocking reading the SharedPreferences as if mMockSharedPreferences was previously written
		// correctly.
		/*inserting data into our Mock SharedPreferences Successfully and then returning back their value */
		`when`(mMockSharedPreferences.getString(eq(SharedPreferencesHelper.NAME), anyString()))
			.thenReturn(null)
		
		`when`(mMockSharedPreferences.getString(eq(SharedPreferencesHelper.EMAIL), anyString()))
			.thenReturn(user.email)
		
		`when`(mMockSharedPreferences.getLong(eq(SharedPreferencesHelper.DOB), anyLong()))
			.thenReturn(user.dob.timeInMillis)
		
		// Mocking a successful commit.
		`when`(mMockEditor.commit()).thenReturn(true)
		
		// Return the MockEditor when requesting it.
		`when`(mMockSharedPreferences.edit()).thenReturn(mMockEditor)
		return SharedPreferencesHelper(mMockSharedPreferences)
	}
	
	private fun createBrokenMockSharedPreference(): SharedPreferencesHelper {
		// Mocking a commit that fails.
		lenient().`when`(mMockBrokenEditor.commit()).thenReturn(false)
		// Return the broken MockEditor when requesting it.
		`when`(mMockBrokenSharedPreferences.edit()).thenReturn(mMockBrokenEditor)
		return SharedPreferencesHelper(mMockBrokenSharedPreferences)
	}
	
	@Test
	fun sharedPreferencesHelper_SavePersonalInformationFailed_ReturnsFalse() {
		// Read personal information from a broken SharedPreferencesHelper
		val success = mMockBrokenSharedPreferencesHelper.saveUserInfo(user)
		assertThat(
			"Makes sure writing to a broken SharedPreferencesHelper returns false", success,
			`is`(false)
		)
	}
	
}

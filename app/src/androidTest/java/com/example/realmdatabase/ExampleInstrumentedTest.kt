package com.example.realmdatabase

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkAllComponentIsVisible_isSuccess() {
        onView(withId(R.id.fabAddContact))
            .check(matches(isDisplayed()))
            .perform(click())

        val name = "Nikita"

        onView(withId(R.id.etName))
            .check(matches(isDisplayed()))
            .perform(typeText(name))
            .check(matches(withText(name)))

        val surname = "Panchenko"

        onView(withId(R.id.etSurname))
            .check(matches(isDisplayed()))
            .perform(typeText(surname))
            .check(matches(withText(surname)))

        val phone = "+799999999"

        onView(withId(R.id.etNumber))
            .check(matches(isDisplayed()))
            .perform(typeText(phone))
            .check(matches(withText(phone)))

        onView(withId(R.id.btnSave))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.rvContacts))
            .check(matches(isDisplayed()))
    }
}
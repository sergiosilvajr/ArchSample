package com.sergiosilvajr.archsample

import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.action.ViewActions.click

@RunWith(AndroidJUnit4::class)
class MainActivityTest() {

    @get:Rule var activity = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun clickOnPlus() {
        onView(withId(R.id.textView)).check(matches(withText("0")))

        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun clickOnMinus() {
        onView(withId(R.id.textView)).check(matches(withText("0")))

        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("-1")))

    }
}
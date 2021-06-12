package com.starter.view.activity

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import com.google.common.truth.Truth.assertThat
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.starter.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun verifyInject(){
        ActivityScenario.launch(MainActivity::class.java).use {
            it.moveToState(Lifecycle.State.CREATED)
            it.onActivity { activity ->
                assertThat(activity.viewModel).isNotNull()
            }
        }
    }
    @Test
    fun edit_Text_is_Visible(){
        Espresso.onView(ViewMatchers.withId(R.id.et_currency))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_type_text_inside_edit_text(){
        Espresso.onView(ViewMatchers.withId(R.id.et_currency))
            .perform(ViewActions.clearText())
            .perform(ViewActions.typeText("123"))
            .check(ViewAssertions.matches(ViewMatchers.withText("123")))
    }
}
package com.rick.guess2

import android.content.res.Resources
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest {
    @Rule
    @JvmField
    val activityScenarioRule = ActivityScenarioRule<MaterialActivity>(MaterialActivity::class.java)
    lateinit var resources : Resources
    @Test
    fun guessWrong(){

        var secret = 0

        activityScenarioRule.scenario.onActivity {
            secret = it.secretNumber.secret
            resources = it.resources
        }

        for (n in 1..10){
            if (n != secret){
                onView(withId(R.id.ed_number)).perform(clearText())
                onView(withId(R.id.ed_number)).perform(typeText(n.toString()))
                onView(withId(R.id.ok_button)).perform(click())

                val message =
                    if (n < secret) resources.getString(R.string.bigger)
                    else resources.getString(R.string.smaller)
                onView(withText(message)).check(matches(isDisplayed())) //確認message中的文字是否有顯示出來
                onView(withText(resources.getString(R.string.ok))).perform(click())
            }
        }

    }
    @Test
    fun clickfab(){
        /*activityScenarioRule.scenario.onActivity {
            resources = it.resources
        }*/
        onView(withId(R.id.fab)).perform(click())
//        onView(withText(resources.getString(R.string.replay_game))).check(matches(isDisplayed()))
        onView(withText(R.string.ok)).perform(click())

        val checkCount = 0
        onView(withId(R.id.record_counter)).check(matches(withText(checkCount.toString())))
    }
}
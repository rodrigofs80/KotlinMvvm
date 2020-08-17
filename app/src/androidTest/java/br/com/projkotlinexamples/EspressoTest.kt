package br.com.projkotlinexamples

import android.util.Log
import br.com.projkotlinexamples.ui.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import br.com.projkotlinexamples.ui.adapter.MainAdapter

@RunWith(AndroidJUnit4::class)
class EspressoTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickItemListShowDetail() {
        Log.i("*** ", "Inicio do teste")
        Intents.init()
        Thread.sleep(1000)
        onView(withId(R.id.recycler)).perform(actionOnItemAtPosition<MainAdapter.MainViewHolder>(1, click()))
        onView(withId(R.id.avatar)).check(matches(isDisplayed()));
        Log.i("*** ", "Fim do teste")
        Intents.release()
    }
}
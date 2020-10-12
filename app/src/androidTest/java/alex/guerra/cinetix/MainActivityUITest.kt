package alex.guerra.cinetix

import alex.guerra.cinetix.ui.main.MainActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test

class MainActivityUITest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule =
        GrantPermissionRule.grant(
            "android.permission.ACCESS_COARSE_LOCATION"
        )

    @Test
    fun testRecyclerViewIsVisible() {
        onView(withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    `is`(mActivityTestRule.activity.window.decorView)
                )
            )
            .check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewPerformClick() {
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ViewHolder>(0, click()))
    }

    @Test
    fun testRecyclerViewPerformScrollToLastItem() {
        val rv = mActivityTestRule.activity.findViewById<RecyclerView>(R.id.recyclerView)
        val count = rv.adapter?.itemCount as Int

        onView(withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    `is`(mActivityTestRule.activity.window.decorView)
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<ViewHolder>(count - 1))
    }
}

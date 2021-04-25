package com.bagasbest.beoskop21.view.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.model.utils.DummyData
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovies = DummyData.generateDummyMovie()
    private val dummySeries = DummyData.generateDummyTvSeries()

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovieData() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_poster)).check(matches(dummyMovies[0].poster?.let { withText(it) }))
        onView(withId(R.id.detail_launch_date)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_launch_date)).check(matches(withText(dummyMovies[0].launchDate)))
        onView(withId(R.id.detail_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_duration)).check(matches(withText(dummyMovies[0].duration)))
        onView(withId(R.id.detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(withText(dummyMovies[0].description)))
    }

    @Test
    fun loadSeriesData() {
        onView(withText(R.string.series)).perform(click())
        onView(withId(R.id.rv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummySeries.size))
    }

    @Test
    fun loadDetailSeries() {
        onView(withText(R.string.series)).perform(click())
        onView(withId(R.id.rv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dummySeries[0].title)))
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_poster)).check(matches(dummySeries[0].poster?.let { withText(it) }))
        onView(withId(R.id.detail_launch_date)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_launch_date)).check(matches(dummySeries[0].year?.let { withText(it) }))
        onView(withId(R.id.detail_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_duration)).check(matches(withText(dummySeries[0].durationEpisode)))
        onView(withId(R.id.detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(withText(dummySeries[0].description)))
        onView(withId(R.id.detail_streaming)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_streaming)).check(matches(withText(dummySeries[0].streamingOn)))
    }
}
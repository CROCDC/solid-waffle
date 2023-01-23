package com.crocdc.solidwaffle.fragments

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.util.MockFactory
import com.crocdc.solidwaffle.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LocationAreaFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun showNameAndPokemons() {
        launchFragmentInHiltContainer<LocationAreaFragment>(
            bundleOf("id" to "id")
        )

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_name),
                ViewMatchers.withText(MockFactory.areaName)
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_name),
                ViewMatchers.withText(MockFactory.pokemonName)
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
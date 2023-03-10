package com.crocdc.solidwaffle.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.crocdc.solidwaffle.util.MockFactory
import com.crocdc.solidwaffle.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PokemonListingFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun recyclerShowListItems() {
        launchFragmentInHiltContainer<PokemonListingFragment>()
        onView(withText(MockFactory.pokemonName)).check(matches(isDisplayed()))
    }
}

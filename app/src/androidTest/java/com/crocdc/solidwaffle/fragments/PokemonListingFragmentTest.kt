package com.crocdc.solidwaffle.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.di.FakePokemonListingUseCaseImp
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
        launchFragmentInHiltContainer<PokemonListingFragment>(R.style.Theme_SolidWaffle)
        onView(withText(FakePokemonListingUseCaseImp.name)).check(matches(isDisplayed()))
    }
}

package com.crocdc.solidwaffle.fragments

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.di.FakePokemonInfoUseCaseImp
import com.crocdc.solidwaffle.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PokemonInfoFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun showPokemonNameAndTypes() {
        launchFragmentInHiltContainer<PokemonInfoFragment>(
            R.style.Theme_SolidWaffle,
            bundleOf("name" to FakePokemonInfoUseCaseImp.name)
        )
        onView(
            allOf(
                withId(R.id.txt_name),
                withText(FakePokemonInfoUseCaseImp.name)
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.txt_name),
                withText(FakePokemonInfoUseCaseImp.types.first().name)
            )
        ).check(matches(isDisplayed()))
    }
}
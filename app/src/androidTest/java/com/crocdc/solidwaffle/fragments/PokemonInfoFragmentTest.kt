package com.crocdc.solidwaffle.fragments

import android.view.View
import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.crocdc.solidwaffle.R
import com.crocdc.solidwaffle.di.FakePokemonInfoUseCaseImp
import com.crocdc.solidwaffle.util.MockFactory
import com.crocdc.solidwaffle.util.launchFragmentInHiltContainer
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PokemonInfoFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val bundle = bundleOf("name" to MockFactory.pokemonName)

    @Test
    fun showPokemonNameAndTypes() {
        launchFragmentInHiltContainer<PokemonInfoFragment>(bundle)
        onView(
            allOf(
                withId(R.id.txt_name),
                withText(MockFactory.pokemonName)
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.txt_name),
                withText(FakePokemonInfoUseCaseImp.types.first().name)
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun showEvolutions() {
        launchFragmentInHiltContainer<PokemonInfoFragment>(bundle)
        onView(
            allOf(
                withId(R.id.txt_from),
                withText(MockFactory.fromEvolutionTo.from.name)
            )
        ).check(matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.txt_to),
                withText(MockFactory.fromEvolutionTo.to.name)
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun showMoves() {
        launchFragmentInHiltContainer<PokemonInfoFragment>(bundle)
        onView(withId(R.id.tab_layout)).perform(selectTabAtPosition(1))
        onView(
            allOf(
                withId(R.id.txt_name),
                withText(MockFactory.move.name)
            )
        ).check(matches(isDisplayed()))
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() =
                allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }
}

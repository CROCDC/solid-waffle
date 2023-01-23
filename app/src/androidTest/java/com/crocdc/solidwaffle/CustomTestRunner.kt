package com.crocdc.solidwaffle

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.crocdc.di.UseCaseModule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules

@HiltAndroidTest
@UninstallModules(UseCaseModule::class)
class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
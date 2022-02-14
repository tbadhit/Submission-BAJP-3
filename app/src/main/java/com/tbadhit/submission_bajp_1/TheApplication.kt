package com.tbadhit.submission_bajp_1

import android.app.Application
import com.tbadhit.submission_bajp_1.di.appModule
import com.tbadhit.submission_bajp_1.di.databaseModule
import com.tbadhit.submission_bajp_1.di.repositoryModule
import com.tbadhit.submission_bajp_1.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TheApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TheApplication)
            modules(
                listOf(
                    appModule,
                    repositoryModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }
}
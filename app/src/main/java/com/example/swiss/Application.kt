package com.example.swiss

import android.app.Application
import com.example.swiss.network.ktorModule
import com.example.swiss.repo.repoKoinModule
import com.example.swiss.ui.di.viewModelKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@Application)
            modules(
                ktorModule,
                repoKoinModule,
                viewModelKoinModule
            )
        }
    }
}
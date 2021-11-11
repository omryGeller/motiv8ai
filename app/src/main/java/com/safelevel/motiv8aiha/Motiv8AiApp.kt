package com.safelevel.motiv8aiha

import android.app.Application
import com.safelevel.motiv8aiha.modules.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Motiv8AiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(mainModule))
        }
    }
}
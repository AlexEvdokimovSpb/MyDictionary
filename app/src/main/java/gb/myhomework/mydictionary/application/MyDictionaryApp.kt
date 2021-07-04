package gb.myhomework.mydictionary.application

import android.app.Application
import gb.myhomework.mydictionary.di.application
import gb.myhomework.mydictionary.di.historyScreen
import gb.myhomework.mydictionary.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyDictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}
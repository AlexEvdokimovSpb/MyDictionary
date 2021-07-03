package gb.myhomework.mydictionary.application

import android.app.Application
import gb.myhomework.mydictionary.di.application
import gb.myhomework.mydictionary.di.mainScreen
import org.koin.core.context.startKoin

class MyDictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}
package gb.myhomework.mydictionary.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyDictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@MyDictionaryApp) }
    }
}
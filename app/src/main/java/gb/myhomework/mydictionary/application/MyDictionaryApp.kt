package gb.myhomework.mydictionary.application

import android.app.Application
import gb.myhomework.mydictionary.di.AppComponent
import gb.myhomework.mydictionary.di.DaggerAppComponent

class MyDictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val component = DaggerAppComponent.builder()
            .appContext(this).build()
        MyDictionaryApp.component = component

    }

    companion object {
        lateinit var component: AppComponent
    }
}
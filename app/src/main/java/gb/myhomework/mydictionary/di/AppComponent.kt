package gb.myhomework.mydictionary.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import gb.myhomework.mydictionary.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}


package gb.myhomework.mydictionary.di

import gb.myhomework.mydictionary.interactor.MainInteractor
import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.model.datasource.RetrofitImplementation
import gb.myhomework.mydictionary.model.datasource.RoomDataBaseImplementation
import gb.myhomework.mydictionary.model.repository.Repository
import gb.myhomework.mydictionary.model.repository.RepositoryImplementation
import gb.myhomework.mydictionary.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory {
        MainInteractor(
            repositoryRemote = get(qualifier = named(NAME_REMOTE)),
            repositoryLocal = get(qualifier = named(NAME_LOCAL))
        )
    }
    viewModel {
        MainViewModel(interactor = get())
    }
}

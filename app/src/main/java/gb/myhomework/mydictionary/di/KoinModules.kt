package gb.myhomework.mydictionary.di

import androidx.room.Room
import gb.myhomework.mydictionary.interactor.HistoryInteractor
import gb.myhomework.mydictionary.interactor.MainInteractor
import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.model.datasource.RetrofitImplementation
import gb.myhomework.mydictionary.model.datasource.RoomDataBaseImplementation
import gb.myhomework.mydictionary.model.repository.Repository
import gb.myhomework.mydictionary.model.repository.RepositoryImplementation
import gb.myhomework.mydictionary.model.repository.RepositoryImplementationLocal
import gb.myhomework.mydictionary.model.repository.RepositoryLocal
import gb.myhomework.mydictionary.room.HistoryDataBase
import gb.myhomework.mydictionary.viewmodel.HistoryViewModel
import gb.myhomework.mydictionary.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build()
    }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    viewModel { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    viewModel { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}


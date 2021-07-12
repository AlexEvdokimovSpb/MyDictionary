package gb.myhomework.mydictionary.di

import androidx.room.Room
import gb.myhomework.historyscreen.HistoryInteractor
import gb.myhomework.mydictionary.interactor.MainInteractor
import gb.myhomework.model.DataModel
import gb.myhomework.repository.RetrofitImplementation
import gb.myhomework.repository.RoomDataBaseImplementation
import gb.myhomework.repository.Repository
import gb.myhomework.repository.RepositoryImplementation
import gb.myhomework.repository.RepositoryImplementationLocal
import gb.myhomework.repository.RepositoryLocal
import gb.myhomework.repository.room.HistoryDataBase
import gb.myhomework.historyscreen.HistoryViewModel
import gb.myhomework.mydictionary.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(get(), gb.myhomework.repository.room.HistoryDataBase::class.java, "HistoryDB").build()
    }
    single { get<gb.myhomework.repository.room.HistoryDataBase>().historyDao() }
    single<gb.myhomework.repository.Repository<List<gb.myhomework.model.DataModel>>> {
        gb.myhomework.repository.RepositoryImplementation(gb.myhomework.repository.RetrofitImplementation())
    }
    single<gb.myhomework.repository.RepositoryLocal<List<gb.myhomework.model.DataModel>>> {
        gb.myhomework.repository.RepositoryImplementationLocal(
            gb.myhomework.repository.RoomDataBaseImplementation(
                get()
            )
        )
    }
}

val mainScreen = module {
    viewModel { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    viewModel { gb.myhomework.historyscreen.HistoryViewModel(get()) }
    factory { gb.myhomework.historyscreen.HistoryInteractor(get(), get()) }
}


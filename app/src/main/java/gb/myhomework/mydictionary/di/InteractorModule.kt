package gb.myhomework.mydictionary.di

import dagger.Module
import dagger.Provides
import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.model.repository.Repository
import gb.myhomework.mydictionary.presenter.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}

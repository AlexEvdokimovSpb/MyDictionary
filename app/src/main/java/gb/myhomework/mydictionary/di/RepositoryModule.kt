package gb.myhomework.mydictionary.di

import dagger.Module
import dagger.Provides
import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.model.datasource.DataSource
import gb.myhomework.mydictionary.model.repository.Repository
import gb.myhomework.mydictionary.model.repository.RepositoryImplementation
import gb.myhomework.onlinedictionary.model.datasourse.RetrofitImplementation
import gb.myhomework.onlinedictionary.model.datasourse.RoomDataBaseImplementation
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(
        @Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(
        @Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> =
        RoomDataBaseImplementation()
}

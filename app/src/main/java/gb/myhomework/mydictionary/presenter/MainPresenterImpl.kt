package gb.myhomework.mydictionary.presenter

import gb.myhomework.mydictionary.model.data.AppState
import gb.myhomework.mydictionary.model.repository.RepositoryImplementation
import gb.myhomework.mydictionary.rx.SchedulerProvider
import gb.myhomework.mydictionary.ui.base.MVPView
import gb.myhomework.onlinedictionary.model.datasourse.DataSourceLocal
import gb.myhomework.onlinedictionary.model.datasourse.DataSourceRemote
import io.reactivex.disposables.CompositeDisposable

class MainPresenterImpl<T : AppState, V : MVPView>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribe({
                    currentView?.renderData(it)
                }, {
                    currentView?.renderData(AppState.Error(it))
                })
        )
    }
}

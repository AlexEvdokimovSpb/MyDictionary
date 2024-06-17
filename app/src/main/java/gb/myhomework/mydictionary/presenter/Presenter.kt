package gb.myhomework.mydictionary.presenter

import gb.myhomework.mydictionary.model.data.AppState
import gb.myhomework.mydictionary.ui.base.MVPView

interface Presenter<T : AppState, V : MVPView> {

    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}

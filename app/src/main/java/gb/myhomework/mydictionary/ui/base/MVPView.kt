package gb.myhomework.mydictionary.ui.base

import gb.myhomework.mydictionary.model.data.AppState

interface MVPView {
    fun renderData(appState: AppState)
}

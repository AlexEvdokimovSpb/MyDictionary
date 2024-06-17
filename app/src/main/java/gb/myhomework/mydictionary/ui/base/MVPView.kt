package gb.myhomework.mydictionary.ui.base

import gb.myhomework.model.AppState

interface MVPView {
    fun renderData(appState: gb.myhomework.model.AppState)
}

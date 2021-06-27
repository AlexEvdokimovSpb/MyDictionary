package gb.myhomework.mydictionary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import gb.myhomework.mydictionary.model.data.AppState
import gb.myhomework.mydictionary.presenter.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), MVPView {

    protected lateinit var presenter: Presenter<T, MVPView>

    protected abstract fun createPresenter(): Presenter<T, MVPView>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}

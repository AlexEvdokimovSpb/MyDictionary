package gb.myhomework.mydictionary.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import gb.myhomework.mydictionary.R
import gb.myhomework.mydictionary.model.data.AppState
import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.presenter.MainPresenterImpl
import gb.myhomework.mydictionary.presenter.Presenter
import gb.myhomework.mydictionary.ui.adapter.MainAdapter
import gb.myhomework.mydictionary.ui.base.BaseActivity
import gb.myhomework.mydictionary.ui.base.MVPView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<AppState>() {

    private var adapter: MainAdapter? = null
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun createPresenter(): Presenter<AppState, MVPView> {
        return MainPresenterImpl()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_imageView.setOnClickListener {
            val searchWord = search_edit_text.text.toString()
                presenter.getData(searchWord, true)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        main_activity_recyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        main_activity_recyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    progress_bar_horizontal.visibility = View.VISIBLE
                    progress_bar_round.visibility = View.GONE
                    progress_bar_horizontal.progress = appState.progress
                } else {
                    progress_bar_horizontal.visibility = View.GONE
                    progress_bar_round.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        error_textview.text = error ?: getString(R.string.undefined_error)
        reload_button.setOnClickListener {
            presenter.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        success_linear_layout.visibility = View.VISIBLE
        loading_frame_layout.visibility = View.GONE
        error_linear_layout.visibility = View.GONE
    }

    private fun showViewLoading() {
        success_linear_layout.visibility = View.GONE
        loading_frame_layout.visibility = View.VISIBLE
        error_linear_layout.visibility = View.GONE
    }

    private fun showViewError() {
        success_linear_layout.visibility = View.GONE
        loading_frame_layout.visibility = View.GONE
        error_linear_layout.visibility = View.VISIBLE
    }

}
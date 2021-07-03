package gb.myhomework.mydictionary.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import gb.myhomework.mydictionary.R
import gb.myhomework.mydictionary.interactor.MainInteractor
import gb.myhomework.mydictionary.model.data.AppState
import gb.myhomework.mydictionary.model.data.DataModel
import gb.myhomework.mydictionary.ui.adapter.MainAdapter
import gb.myhomework.mydictionary.ui.base.BaseActivity
import gb.myhomework.mydictionary.utils.isOnline
import gb.myhomework.mydictionary.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    override val model: MainViewModel by viewModel()

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initViews()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    adapter.setData(dataModel)
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

    private fun initViewModel() {
        if (main_activity_recyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        main_activity_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        main_activity_recyclerview.adapter = adapter

        search_imageView.setOnClickListener {
            val isNetworkAvailable = isOnline(applicationContext)
            if (isNetworkAvailable) {
                val searchWord = search_edit_text.text.toString()
                model.getData(searchWord, true)
            } else {
                showNoInternetConnectionDialog()
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        error_textview.text = error ?: getString(R.string.undefined_error)
        reload_button.setOnClickListener {
            model.getData("hi", true)
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
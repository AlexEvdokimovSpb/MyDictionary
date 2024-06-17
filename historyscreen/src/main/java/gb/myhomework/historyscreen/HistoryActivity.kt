package gb.myhomework.historyscreen

import android.os.Bundle
import androidx.lifecycle.Observer
import gb.myhomework.core.base.BaseActivity
import gb.myhomework.historyscreen.*
import gb.myhomework.model.AppState
import gb.myhomework.model.DataModel
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.android.scope.currentScope

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    override val layoutRes = R.layout.activity_history
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        check(history_activity_recyclerview.adapter == null) { "The ViewModel should be initialised first" }
        injectDependencies()
        val viewModel: HistoryViewModel by currentScope.inject()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        history_activity_recyclerview.adapter = adapter
    }
}

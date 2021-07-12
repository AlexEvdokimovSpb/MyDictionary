package gb.myhomework.mydictionary.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import gb.myhomework.mydictionary.R
import gb.myhomework.mydictionary.utils.isOnline
import kotlinx.android.synthetic.main.activity_description.*

class DescriptionActivity : AppCompatActivity() {

    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        setActionbarHomeButtonAsUp()
        description_layout.setOnRefreshListener { startLoadingOrShowError() }
        setData()
        expandImage()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setData() {
        val bundle = intent.extras
        description_header.text = bundle?.getString(WORD_EXTRA)
        description_textview.text = bundle?.getString(DESCRIPTION_EXTRA)
        val imageLink = bundle?.getString(URL_EXTRA)
        if (imageLink.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
            usePicassoToLoadPhoto(description_imageview, imageLink)
        }
    }

    private fun startLoadingOrShowError() {
        if (isOnline(applicationContext)) {
            setData()
        } else {
            gb.myhomework.utils.AlertDialogFragment.newInstance(
                getString(R.string.dialog_title_device_is_offline),
                getString(R.string.dialog_message_device_is_offline)
            ).show(
                supportFragmentManager,
                DIALOG_FRAGMENT_TAG
            )
            stopRefreshAnimationIfNeeded()
        }
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (description_layout.isRefreshing) {
            description_layout.isRefreshing = false
        }
    }

    private fun usePicassoToLoadPhoto(imageView: ImageView, imageLink: String) {
        Picasso.with(applicationContext).load("https:$imageLink")
            .placeholder(R.drawable.ic_no_photo_vector)
            .error(R.drawable.ic_load_error_vector)
            .fit()
            .centerCrop()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    stopRefreshAnimationIfNeeded()
                }

                override fun onError() {
                    stopRefreshAnimationIfNeeded()
                }
            })
    }

    private fun expandImage() {
        description_imageview.setOnClickListener {
            isExpanded = !isExpanded
            TransitionManager.beginDelayedTransition(
                description_group, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )

            val params: ViewGroup.LayoutParams = description_imageview.layoutParams
            params.height =
                if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
            description_imageview.layoutParams = params
            description_imageview.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }
    }

    companion object {

        private const val DIALOG_FRAGMENT_TAG = "ui.descriptionActivity.dialog"

        private const val WORD_EXTRA = "ui.descriptionActivity.word"
        private const val DESCRIPTION_EXTRA = "ui.descriptionActivity.description"
        private const val URL_EXTRA = "ui.descriptionActivity.url"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            url: String?
        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
            putExtra(DESCRIPTION_EXTRA, description)
            putExtra(URL_EXTRA, url)
        }
    }
}

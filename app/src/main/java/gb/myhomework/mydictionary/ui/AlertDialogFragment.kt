package gb.myhomework.mydictionary.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import gb.myhomework.mydictionary.R

class AlertDialogFragment : DialogFragment() {

    private lateinit var title: String
    private lateinit var message: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            title = getString(R.string.attention)
            message = getString(R.string.no_network)

            builder.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_baseline_not_interested_24)
                .setPositiveButton(getString(R.string.warning_accepted)) { dialog, id ->
                    dialog.cancel()
                }
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String?, message: String?) =
            AlertDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(title, message)
                }
            }
    }
}
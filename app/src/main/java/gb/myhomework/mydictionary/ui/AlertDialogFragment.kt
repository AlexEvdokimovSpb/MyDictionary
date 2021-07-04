package gb.myhomework.mydictionary.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import gb.myhomework.mydictionary.utils.getAlertDialog
import gb.myhomework.mydictionary.utils.getStubAlertDialog

class AlertDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = activity
        var alertDialog = getStubAlertDialog(context!!)
        val args = arguments
        if (args != null) {
            val title = args.getString(TITLE_EXTRA)
            val message = args.getString(MESSAGE_EXTRA)
            alertDialog = getAlertDialog(context, title, message)
        }
        return alertDialog
    }

    companion object {

        private const val TITLE_EXTRA = "mydictionary.ui.alertDialogFragment.title"
        private const val MESSAGE_EXTRA = "mydictionary.ui.alertDialogFragment.message"

        fun newInstance(title: String?, message: String?): AlertDialogFragment {
            val dialogFragment = AlertDialogFragment()
            val args = Bundle()
            args.putString(TITLE_EXTRA, title)
            args.putString(MESSAGE_EXTRA, message)
            dialogFragment.arguments = args
            return dialogFragment
        }
    }
}
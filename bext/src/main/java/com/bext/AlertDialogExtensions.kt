package com.bext

import android.content.Context
import android.support.v7.app.AlertDialog
import kotlin.properties.Delegates

/**
 * Created by Birju Vachhani on 01/11/18.
 */

fun Context.alertDialog(dialogBuilder: DialogBuilder.() -> Unit) {
    DialogBuilder(this).apply {
        dialogBuilder()
    }.createDialog().show()
}

class DialogBuilder(val context: Context) {

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)

    var message: String by Delegates.observable(context.getString(R.string.dialog_message_default)) { _, _, newValue ->
        builder.setMessage(newValue)
    }

    var messageId: Int by Delegates.observable(0) { _, _, newValue ->
        builder.setMessage(newValue)
    }

    var titleId: Int by Delegates.observable(0) { _, _, newValue ->
        builder.setTitle(newValue)
    }

    var positiveButtonId: Int by Delegates.observable(0) { _, _, newValue ->
        builder.setPositiveButton(newValue) { dialog, _ ->
            positiveButtonClick.invoke()
            dialog.dismiss()
        }
    }

    var negativeButtonId: Int by Delegates.observable(0) { _, _, newValue ->
        builder.setNegativeButton(newValue) { dialog, _ ->
            negativeButtonClick.invoke()
            dialog.dismiss()
        }
    }

    var title: String by Delegates.observable(context.getString(R.string.app_name)) { _, _, newValue ->
        builder.setTitle(newValue)
    }

    var positiveButtonText: String by Delegates.observable(context.getString(R.string.positive_button_default_text)) { _, _, newValue ->
        builder.setPositiveButton(newValue) { dialog, _ ->
            positiveButtonClick.invoke()
            dialog.dismiss()
        }
    }

    var negativeButtonText: String by Delegates.observable(context.getString(R.string.negative_button_default_text)) { _, _, newValue ->
        builder.setNegativeButton(newValue) { dialog, _ ->
            negativeButtonClick.invoke()
            dialog.dismiss()
        }
    }

    var positiveButtonClick: (() -> Unit) by Delegates.observable({}) { _, _, newValue ->
        builder.setPositiveButton(positiveButtonText) { dialog, _ ->
            newValue.invoke()
            dialog.dismiss()
        }
    }
    var negativeButtonClick: (() -> Unit) by Delegates.observable({}) { _, _, newValue ->
        builder.setNegativeButton(negativeButtonText) { dialog, _ ->
            newValue.invoke()
            dialog.dismiss()
        }
    }

    internal fun createDialog(): AlertDialog {
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setPositiveButton(positiveButtonText) { dialog, _ ->
            positiveButtonClick()
            dialog.dismiss()
        }
        return builder.create()
    }
}


/*
 * Copyright 2019 BirjuVachhani
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.birjuvachhani.baseextensions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bext.alertDialog
import com.bext.toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Activity class to handle handle MainActivity UI
 *
 * */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnShowDialog.setOnClickListener {
            displayDialog()
        }
    }

    /**
     * This will be executed on the button click in main activity
     *
     * */
    private fun displayDialog() {
        alertDialog {
            title = "Tips"
            message = "This is the awesome form of kotlin DSL."
            positiveButton {
                text = R.string.positive
                onClick {
                    toast("Cool")
                }
            }
            negativeButton {
                text = R.string.negative
                onClick {
                    toast("Whatever")
                }
            }
        }
    }
}
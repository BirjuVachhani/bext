/*
 * Copyright 2018 BirjuVachhani
 * </p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * </p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * </p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.birjuvachhani.baseextensions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bext.alertDialog
import com.bext.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun displayDialog(v: View) {
        alertDialog {
            title = "Tips"
            message = "This is the awesome form of kotlin DSL."
            positiveButton {
                text = from(R.string.positive)
                onClick {
                    toast("Cool")
                }
            }
            negativeButton {
                text = from(R.string.negative)
                onClick {
                    toast("Whatever")
                }
            }
        }
    }
}
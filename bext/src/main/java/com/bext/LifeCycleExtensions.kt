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

package com.bext

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

/**
 * Created by Birju Vachhani on 16-12-2018.
 */

/**
 * Extension function for observing [LiveData]
 * @param owner is [LifecycleOwner] which will be used to listen lifecycle changes
 * @param func is a function which will be executed whenever [LiveData] is changed
 * */
fun <T> LiveData<T>.watch(owner: LifecycleOwner, func: (T) -> Unit) =
    this.observe(owner, Observer<T> { t ->
        t?.run {
            func.invoke(this)
        }
    })

/**
 * Extension function for [MutableLiveData] to return as Immutable or as [LiveData]
 * */
fun <T> MutableLiveData<T>.immutable(): LiveData<T> =
    this as? LiveData<T> ?: throw ClassCastException("Cannot convert Mutable LiveData to Immutable")

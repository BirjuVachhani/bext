/*
 * Copyright 2019 BirjuVachhani (https://github.com/BirjuVachhani)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bext.arch

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/*
 * Created by Birju Vachhani on 16 December 2018
 * Copyright © 2019 bext. All rights reserved.
 */

/**
 * Extension function for observing [LiveData]
 * @param owner is [LifecycleOwner] which will be used to listen lifecycle changes
 * @param func is a function which will be executed whenever [LiveData] is changed
 * */
fun <T> LiveData<T>.watch(owner: LifecycleOwner, func: (T) -> Unit) =
    this.observe(owner, Observer<T> { t -> t?.let(func) })

/**
 * Extension function for observing [LiveData]
 * @param owner is [LifecycleOwner] which will be used to listen lifecycle changes
 * @param func is a function which will be executed whenever [LiveData] is changed
 * */
fun <T> MutableLiveData<T>.watch(owner: LifecycleOwner, func: (T) -> Unit) =
    this.observe(owner, Observer<T> { t -> t?.let(func) })

/**
 * Extension function for [MutableLiveData] to return as Immutable or as [LiveData]
 * */
fun <T> MutableLiveData<T>.immutable(): LiveData<T> =
    this as? LiveData<T> ?: throw ClassCastException("Cannot convert Mutable LiveData to Immutable")

/**
 * Extension function for notifying observers of the [MutableLiveData]
 * When there's some change in the value of [MutableLiveData] but the whole value
 * is not changed/replaced, observers are not notified. This extension assigns same value to the [MutableLiveData]
 * that triggers and passes update to its observers
 * */
fun <T> MutableLiveData<T>.notify() {
    this.value = this.value
}

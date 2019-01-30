package com.bext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

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

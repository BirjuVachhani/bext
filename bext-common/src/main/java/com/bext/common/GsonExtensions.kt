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

package com.bext.common

import com.google.gson.Gson

/*
 * Created by Birju Vachhani on 30 January 2019
 * Copyright © 2019 bext. All rights reserved.
 */

val gson = Gson()

/**
 * Clones an object using [Gson]
 * */
inline fun <reified T> T.createClone(): T {
    return gson.fromJson(gson.toJson(this), T::class.java)
}

/**
 * creates a clone of given [ArrayList] using gson
 * */
inline fun <reified T> ArrayList<T>.createClone(): ArrayList<T> {
    val list = ArrayList<T>()
    this.forEach { item ->
        list.add(item.createClone())
    }
    return list
}
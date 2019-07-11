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

package com.bext.core

import android.os.Handler

/*
 * Created by Birju Vachhani on 16 December 2018
 * Copyright © 2019 bext. All rights reserved.
 */

/**
 * Extension function for posting a [Handler]
 * */
fun postHandler(func: () -> Unit) =
    Handler().post { func() }

/**
 * Extension function for posting a delayed [Handler]
 * */
fun postHandler(delay: Long, func: () -> Unit) =
    Handler().postDelayed(func, delay)
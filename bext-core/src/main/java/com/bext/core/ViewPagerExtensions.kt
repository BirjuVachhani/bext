/*
 * Copyright 2020 BirjuVachhani (https://github.com/BirjuVachhani)
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

import androidx.viewpager.widget.ViewPager

/*
 * Created by Birju Vachhani on 30 January 2019
 * Copyright © 2019 bext. All rights reserved.
 */

/**
 * Helper class for [ViewPager] to add [ViewPager.OnPageChangeListener]
 * */
class PageChangeListener : ViewPager.OnPageChangeListener {

    private var onScrollStateChangedFunc: (state: Int) -> Unit = {}
    private var onScrolledFunc: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit =
        { _, _, _ -> Unit }
    private var onSelectedFunc: (position: Int) -> Unit = {}

    override fun onPageScrollStateChanged(state: Int) =
        onScrollStateChangedFunc(state)

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) =
        onScrolledFunc(position, positionOffset, positionOffsetPixels)

    override fun onPageSelected(position: Int) = onSelectedFunc(position)

    /**
     * sets function to be called when page is selected
     * */
    fun onSelected(func: (position: Int) -> Unit = {}) {
        this.onSelectedFunc = func
    }

    /**
     * sets function to be called when page is scrolled
     * */
    fun onScrolled(func: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit) {
        this.onScrolledFunc = func
    }

    /**
     * sets function to be called when scroll state is changed
     * */
    fun onScrollStateChanged(func: (state: Int) -> Unit) {
        this.onScrollStateChangedFunc = func
    }
}

/**
 * Extension function to add page change listener for [ViewPager]
 * */
fun ViewPager.pageChangeListener(func: PageChangeListener.() -> Unit) =
    this.addOnPageChangeListener(PageChangeListener().apply(func))
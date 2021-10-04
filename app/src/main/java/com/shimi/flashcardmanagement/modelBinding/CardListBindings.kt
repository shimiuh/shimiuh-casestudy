/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shimi.flashcardmanagement.ui

import android.content.Context
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shimi.flashcardmanagement.R
import com.shimi.flashcardmanagement.model.FlashCard

/**
 * [BindingAdapter]s for the [FlashCard]s list.
 */
@BindingAdapter("app:items")
fun setItems(recycler: RecyclerView, items: ArrayList<FlashCard>?) {
    setItemIfNeeded(recycler,items)
}

fun setItemIfNeeded(recycler: RecyclerView, items: ArrayList<FlashCard>?) {
    if(items != null && (recycler.adapter as FlashCardAdapter).currentList != items){
        recycler.layoutAnimation.animation.reset()
        recycler.layoutAnimation = recycler.layoutAnimation
        (recycler.adapter as FlashCardAdapter).submitList(items)
    }
}

//@BindingAdapter("app:cardTest")
//fun setStyle(textView: TextView, enabled: Boolean) {
//
//}

package com.vectormax.streaming.helpers

import android.content.Context
import com.shimi.flashcardmanagement.model.MyObjectBox
import io.objectbox.BoxStore

object Box {
    lateinit var store: BoxStore
        private set

    fun init(context: Context) {
        store = MyObjectBox.builder()
                .androidContext(context.applicationContext)
                .build()
    }
}
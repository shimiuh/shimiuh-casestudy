package com.shimi.flashcardmanagement

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.jacksonandroidnetworking.JacksonParserFactory
import com.vectormax.streaming.helpers.Box
import okhttp3.OkHttpClient

open class AppApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        Box.init(this)

        AndroidNetworking.initialize(getApplicationContext())
        val okHttpClient = OkHttpClient().newBuilder().build()
        AndroidNetworking.initialize(applicationContext, okHttpClient)
        AndroidNetworking.setParserFactory(JacksonParserFactory())
    }


    companion object {
        var mInstance: AppApplication? = null
        @JvmStatic
        fun get(): AppApplication = mInstance!!
    }
}
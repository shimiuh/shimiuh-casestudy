package com.shimi.flashcardmanagement.network

import android.os.Looper
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.shimi.flashcardmanagement.local.Repository
import com.shimi.flashcardmanagement.model.FlashCard
import org.json.JSONArray
import java.net.URLEncoder


class Translator(repository : Repository) {

    private val mRepository = repository

    fun translateHack(text: String, to: String, uniqueId: String, onAdded: Runnable) {
        val url = concatTranslatorUrl(text, to)
        Log.d("FCM", "in AndroidNetworking url :${url} \n text = $text to = $to")
        AndroidNetworking.get(url)
                .setPriority(Priority.LOW)
                .build().getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray) {
                        try {
                            val translation = response.getJSONArray(0)
                            val question = translation.getJSONArray(0).get(0).toString()
                            val answer = translation.getJSONArray(1).get(0).toString()
                            Log.d("FCM", "in onResponse question :$question answer :$answer")
                            mRepository.addCardToDB(FlashCard(0,question,answer,to,uniqueId))
                            onAdded.run()
                        } catch (e: Exception) {
                            Log.e("FCM", " in AndroidNetworking catch" + e.message.toString())
                        }
                    }
                    override fun onError(error: ANError) {
                        Log.d("FCM", " in AndroidNetworking onError ANError:${error.toString()}")
                    }
                })
    }

    private fun concatTranslatorUrl(text: String, to: String):String {
        val sb = StringBuilder()
        sb.append("https://translate.googleapis.com/translate_a/single?client=gtx&sl=us")
        //sb.append(from)
        sb.append("&tl=")
        sb.append(to)
        sb.append("&dt=t&q=")
        sb.append(URLEncoder.encode(text, "utf-8"))//
        return sb.toString()
    }

}

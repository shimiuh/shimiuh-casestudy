@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.vectormax.streaming.helpers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shimi.flashcardmanagement.AppApplication
import com.shimi.flashcardmanagement.model.FlashCard


object PrefsHelper {



    private const val PRIVATE_MODE = 0
    private const val PREF_NAME = "flash_card_management"

    private const val CARD_KEY = "CARD_KEY"
    private const val LAST_LANGUAGE_KEY = "LAST_LANGUAGE_KEY"

    private val mDefaultPrefs = AppApplication.get().getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    fun saveCards(cards: List<FlashCard>) {
        mDefaultPrefs.edit().putString(CARD_KEY,Gson().toJson(cards)).apply()
    }

    fun getCards(): List<FlashCard> {
        val json = mDefaultPrefs.getString(CARD_KEY, null)
        return json?.let { Gson().fromJson(json, object : TypeToken<List<FlashCard>>() {}.type) }
            ?: emptyList()
    }


    fun setLastSelectedLanguage(language: String) {
        mDefaultPrefs.edit().putString(LAST_LANGUAGE_KEY,language).commit()
    }
    fun getSelectedLanguage():String {
        return mDefaultPrefs.getString(LAST_LANGUAGE_KEY, "en").toString()
    }

}
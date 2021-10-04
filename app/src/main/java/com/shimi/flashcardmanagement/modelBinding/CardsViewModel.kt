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
package com.shimi.flashcardmanagement.modelBinding


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shimi.flashcardmanagement.local.Repository
import com.shimi.flashcardmanagement.model.FlashCard
import com.shimi.flashcardmanagement.model.Language
import com.shimi.flashcardmanagement.network.Translator
import com.vectormax.streaming.helpers.PrefsHelper
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

/**
 * ViewModel for the card list screen.
 */
class CardsViewModel(private val mRepository: Repository) : ViewModel() {

    private val mItems: MutableLiveData<ArrayList<FlashCard>> = MutableLiveData()
    private val mTranslator  = Translator(mRepository)

    init {
        Log.d("FCM", "in init CardsViewModel")
    }

    fun getCardList(): LiveData<ArrayList<FlashCard>> {
        loadCards()
        return mItems
    }

    fun loadCards() {
        mItems.value = ArrayList(getCards())
    }

    private fun getCards(): ArrayList<FlashCard> {
        return mRepository.getCardsForLang(PrefsHelper.getSelectedLanguage())
    }

    fun deleteCard(cardId: String) {
        mRepository.deleteCard(cardId)
        loadCards()
    }

    fun addCardsToDB(question: String, answer: String) {
        val uniqueId = UUID.randomUUID().toString()//TODO: make sure this random uniqueId is not in the DB
        mRepository.addCardToDB(FlashCard(0, question, answer, "en",uniqueId))
        //PrefsHelper.setLastSelectedLanguage("en")
        loadCards()

        var count = 0
        val list = mRepository.getSupportedLanguages()
        list.forEach{
            val text = "$question? $answer"  //.replace("?","")
            mTranslator.translateHack(text, it.code,uniqueId) {
                count++
                if(count == list.size) {
                    loadCards()
                }
            }
        }
    }

    fun addSupportedLanguageToDB(lang: Language) {
        mRepository.addSupportedLanguageToDB(lang)
        PrefsHelper.setLastSelectedLanguage(lang.code)
        val list = mRepository.getCardsForLang("en")
        list.forEach{
            val text = "${it.cardQuestion}? ${it.cardAnswer}"  //.replace("?","")
            mTranslator.translateHack(text, lang.code,it.cardId) {
                loadCards()
            }
        }
    }

    fun deleteSupportedLanguageFromDB(lang: Language) {
        mRepository.deleteLanguageCardsFromDB(lang)
        PrefsHelper.setLastSelectedLanguage("en")
        loadCards()
    }


    /**
     * TODO: Called by the Data Binding library and the FAB's click listener.
     */
    fun addNewCard() {

    }

    /**
     * Called by Data Binding.
     */
    fun openCard(cardId: String) {
        //TODO: when card is clicked
    }
}
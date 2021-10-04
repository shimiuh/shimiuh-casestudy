package com.shimi.flashcardmanagement.local

import android.util.Log
import com.shimi.flashcardmanagement.model.FlashCard
import com.shimi.flashcardmanagement.model.FlashCard_
import com.shimi.flashcardmanagement.model.Language
import com.vectormax.streaming.helpers.Box
import kotlinx.android.synthetic.*

class Repository {

    fun addSupportedLanguageToDB(language: Language){
        Log.d("FCM","in addSupportedLanguageToDB language = $language")
        val box= Box.store.boxFor(Language::class.java)
        box.put(language)
    }

    fun getSupportedLanguages():ArrayList<Language>{
        val res = ArrayList(Box.store.boxFor(Language::class.java).all)
        Log.d("FCM","in getSupportedLanguages size = "+res.size)
        return res
    }

    fun addCardToDB(card: FlashCard){
        Log.d("FCM","in addCardToDB start card = $card")

        if(card.languageCode.isEmpty()){
            return
        }
        val box= Box.store.boxFor(FlashCard::class.java)
        box.put(card)
    }
    fun addCardsToDB(cards: ArrayList<FlashCard>){
        val box= Box.store.boxFor(FlashCard::class.java)
        box.put(cards)
    }

    fun getCardsForLang(languageCode:String):ArrayList<FlashCard>{
        return ArrayList(Box.store.boxFor(FlashCard::class.java)
            .query()
            .equal(FlashCard_.languageCode,languageCode)
            .build().find())
    }

    fun clearDB(){
        Box.store.boxFor(FlashCard::class.java).removeAll()
    }

    fun deleteCard(cardId: String) {
        Log.d("FCM","in deleteCard cardId = $cardId")
        val toDelete = Box.store.boxFor(FlashCard::class.java)
                .query()
                .equal(FlashCard_.cardId,cardId)
                .build().find()

        Box.store.boxFor(FlashCard::class.java).remove(toDelete)
    }


    fun deleteLanguageCardsFromDB(language: Language){
        Log.d("FCM","in deleteLanguage language = $language")
        val box= Box.store.boxFor(Language::class.java)
        box.remove(language)

        val toDelete = Box.store.boxFor(FlashCard::class.java)
                .query()
                .equal(FlashCard_.languageCode,language.code)
                .build().find()

        Box.store.boxFor(FlashCard::class.java).remove(toDelete)
    }

}
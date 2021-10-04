package com.shimi.flashcardmanagement.local

import com.shimi.flashcardmanagement.model.Language

object HardCoddedLanguages {

    fun getHardCoddedLanguages(): List<Language> {
        return mHardCoddedLanguages
    }
    //SupportedLanguages
    private val mHardCoddedLanguages = listOf(
        Language(0,"Afrikaans","af"),Language(0,"Albanian","sq"),
        Language(0,"Arabic","ar"),Language(0,"Armenian","hy"),
        Language(0,"Azerbaijani","az"),Language(0,"Basque","eu"),
        Language(0,"Belarusian","be"),Language(0,"Bulgarian","bg"),
        Language(0,"Catalan","ca"),Language(0,"Chinese (Simplified)","zh-CN"),
        Language(0,"Chinese (Traditional)","zh-TW"),Language(0,"Croatian","hr"),
        Language(0,"Croatian","hr"),Language(0,"Czech","cs"),
        Language(0,"Danish","da"),Language(0,"Dutch","nl"),
        Language(0,"English","en"),Language(0,"Estonian","et"),
        Language(0,"Filipino","tl"),Language(0,"Finnish","fi"),
        Language(0,"French","fr"),Language(0,"Galician","gl"),
        Language(0,"Georgian","ka"),Language(0,"German","de"),
        Language(0,"Greek","el"),Language(0,"Haitian Creole","ht"),
        Language(0,"Hebrew","iw"),Language(0,"Hindi","hi"),
        Language(0,"Hungarian","hu"),Language(0,"Icelandic","is"),
        Language(0,"Indonesian","id"),Language(0,"Irish","ga"),
        Language(0,"Italian","it"),Language(0,"Japanese","ja"),
        Language(0,"Korean","ko"),Language(0,"Latvian","lv"),
        Language(0,"Lithuanian","lt"),Language(0,"Macedonian","mk"),
        Language(0,"Malay","ms"),Language(0,"mt","mt"),
        Language(0,"Maltese",""),Language(0,"Norwegian","no"),
        Language(0,"Persian","fa"),Language(0,"Polish","pl"),
        Language(0,"Portuguese","pt"),Language(0,"Romanian","ro"),
        Language(0,"Russian","ru"),Language(0,"Serbian","sr"),
        Language(0,"Slovak","sk"),Language(0,"Slovenian","sl"),
        Language(0,"Spanish","es"),Language(0,"Swahili","sw"),
        Language(0,"Swedish","sv"),Language(0,"Thai","th"),
        Language(0,"Turkish","tr"),Language(0,"Ukrainian","uk"),
        Language(0,"Urdu","ur"),Language(0,"Vietnamese","vi"),
        Language(0,"Welsh","cy"), Language(0,"Yiddish","yi"),
    )
}
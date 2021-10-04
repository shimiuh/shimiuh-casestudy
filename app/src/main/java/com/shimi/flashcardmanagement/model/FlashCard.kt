package com.shimi.flashcardmanagement.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class FlashCard (
    @Id var id: Long = 0,
    var cardQuestion: String = "",
    var cardAnswer: String = "",
    var languageCode: String = "",
    var cardId: String = ""
    )

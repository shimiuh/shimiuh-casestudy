package com.shimi.flashcardmanagement.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Language (
    @Id var id: Long = 0,
    var name: String = "",
    var code: String = ""
    ){
    constructor(name: String) : this(0)
}

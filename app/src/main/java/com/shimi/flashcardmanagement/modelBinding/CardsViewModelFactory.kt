package com.shimi.flashcardmanagement.modelBinding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shimi.flashcardmanagement.local.Repository

class CardsViewModelFactory(repository : Repository) : ViewModelProvider.Factory {
    private val mRepository = repository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CardsViewModel(mRepository) as T
    }
}
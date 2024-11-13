package com.varma.hemanshu.starwars_blasters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.varma.hemanshu.starwars_blasters.repository.StarWarsRepo

class StarWarsVMFactory(
    private val repository: StarWarsRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StarWarsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StarWarsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.github.chufarnovevgeniy.testjokesapp.ui.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.chufarnovevgeniy.testjokesapp.domain.JokesRepo

class JokesViewModelFactory(private val jokesRepo: JokesRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(JokesRepo::class.java)
            .newInstance(jokesRepo)
    }
}
package com.github.chufarnovevgeniy.testjokesapp.ui.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.chufarnovevgeniy.testjokesapp.domain.JokesRepo
import com.github.chufarnovevgeniy.testjokesapp.domain.entities.JokeEntity
import kotlinx.coroutines.launch
import java.lang.Exception

sealed class ViewState {
    data class Success(val jokes: List<JokeEntity>): ViewState()
    object Loading : ViewState()
    object Error : ViewState()
    object Idle : ViewState()
}

class JokesViewModel(
    private val jokesRepo: JokesRepo
): ViewModel() {
    private val _state = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> = _state

    init {
        _state.value = ViewState.Idle
    }

    fun onReloadClicked(count: String) {
        count.toIntOrNull()?.let {
            loadJokes(it)
        }
    }

    private fun loadJokes(count: Int) {
        _state.value = ViewState.Loading

        viewModelScope.launch {
            try {
                val response = jokesRepo.getJokes(count)

                if (response.isSuccessful) {
                    _state.value = ViewState.Success(response.body()?.jokes ?: emptyList())
                } else {
                    _state.value = ViewState.Error
                }
            } catch (e: Exception) {
                _state.value = ViewState.Error
            }
        }
    }
}
package com.github.chufarnovevgeniy.testjokesapp.domain

import com.github.chufarnovevgeniy.testjokesapp.domain.entities.JokesResponseEntity
import retrofit2.Response

interface JokesRepo {
    suspend fun getJokes(count: Int): Response<JokesResponseEntity>
}
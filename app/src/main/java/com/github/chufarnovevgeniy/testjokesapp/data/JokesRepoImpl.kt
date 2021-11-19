package com.github.chufarnovevgeniy.testjokesapp.data

import com.github.chufarnovevgeniy.testjokesapp.domain.JokesRepo
import com.github.chufarnovevgeniy.testjokesapp.domain.entities.JokesResponseEntity
import retrofit2.Response

class JokesRepoImpl(
    private val jokesApi: JokesApi
) : JokesRepo {
    override suspend fun getJokes(count: Int): Response<JokesResponseEntity> {
        return jokesApi.getJokes(count)
    }
}
package com.github.chufarnovevgeniy.testjokesapp.data

import com.github.chufarnovevgeniy.testjokesapp.domain.entities.JokesResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesApi {
    @GET("jokes/random/{count}")
    suspend fun getJokes(
        @Path("count") count: Int,
    ): Response<JokesResponseEntity>
}
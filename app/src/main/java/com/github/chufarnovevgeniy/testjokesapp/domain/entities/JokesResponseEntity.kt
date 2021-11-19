package com.github.chufarnovevgeniy.testjokesapp.domain.entities

import com.google.gson.annotations.SerializedName

data class JokesResponseEntity(
    @SerializedName("type") val type: String,
    @SerializedName("value") val jokes: List<JokeEntity>
)
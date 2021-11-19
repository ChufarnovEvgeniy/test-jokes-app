package com.github.chufarnovevgeniy.testjokesapp.domain.entities

import com.google.gson.annotations.SerializedName

data class JokeEntity(
    @SerializedName("joke") val joke: String
)
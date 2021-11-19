package com.github.chufarnovevgeniy.testjokesapp

import android.app.Application
import androidx.fragment.app.Fragment
import com.github.chufarnovevgeniy.testjokesapp.data.JokesApi
import com.github.chufarnovevgeniy.testjokesapp.data.JokesRepoImpl
import com.github.chufarnovevgeniy.testjokesapp.domain.JokesRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    private val jokesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.JOKES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokesApi::class.java)
    }

    val jokesRepo: JokesRepo by lazy { JokesRepoImpl(jokesApi) }
}

val Fragment.app: App
    get() = requireActivity().application as App
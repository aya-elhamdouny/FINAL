package com.example.afinal.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class RetrofitBuilder {

    companion object{
        private val retrofit by lazy {
           Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build() }

        val api by lazy {
            retrofit.create(ApiInterface::class.java) }
    }

}
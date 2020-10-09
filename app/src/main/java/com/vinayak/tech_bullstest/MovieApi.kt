package com.example.myapplication

import com.vinayak.tech_bullstest.MoviesMainModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET(".")
    fun getMovies(@Query("s")searchString : String,
    @Query("apikey") apiKey: String) : Call<MoviesMainModel>

    companion object{

        operator fun invoke() : MovieApi{

            return Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)
        }
    }
}
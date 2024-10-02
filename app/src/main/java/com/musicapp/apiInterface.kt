package com.musicapp

import java.util.List
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers(
        "x-rapidapi-key: 397a23b7c2msh02b87cca0e5aea8p1a8e9ejsn81389e5116e4",
                "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com"
    )
    @GET("/search") 
    fun getData(@Query("q") query: String): Call<MusicData>

}

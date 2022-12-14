package com.catata.retrofit2example.provider

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import com.catata.retrofit2example.model.DogsResponse

interface APIService {
    @GET
    suspend fun getDogsByBreeds (@Url url: String): Response<DogsResponse>
}
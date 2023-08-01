package com.example.lifecyclelivedataviewmodel.networking

import com.example.lifecyclelivedataviewmodel.model.UserData

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<UserData>>
}
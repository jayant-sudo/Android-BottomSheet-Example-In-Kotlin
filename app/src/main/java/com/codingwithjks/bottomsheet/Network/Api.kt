package com.codingwithjks.bottomsheet.Network

import com.codingwithjks.bottomsheet.Model.Food
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("food.php")
    fun getAllFood(): Call<List<Food>>
}
package com.codingwithjks.bottomsheet.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    fun getRetrofit():Retrofit{
        var retrofit=Retrofit.Builder()
            .baseUrl(Url.urlName)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    var getApi:Api=getRetrofit().create(Api::class.java)
}
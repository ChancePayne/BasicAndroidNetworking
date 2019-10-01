package com.lambdaschool.basicandroidnetworking.retrofit

import com.lambdaschool.basicandroidnetworking.model.AdviceMsg
import retrofit2.Call
import retrofit2.http.GET

interface AdviceAPI {

    @GET("advice")
    fun randomAdvice(): Call<AdviceMsg>
}

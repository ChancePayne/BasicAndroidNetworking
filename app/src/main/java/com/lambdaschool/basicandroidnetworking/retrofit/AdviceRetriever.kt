package com.lambdaschool.basicandroidnetworking.retrofit

import android.util.Log
import com.google.gson.GsonBuilder
import com.lambdaschool.basicandroidnetworking.model.AdviceMsg
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AdviceRetriever {

    companion object {
        private const val TAG = "RETRIEVER"
        internal const val BASE_URL = "https://api.adviceslip.com/"
    }

    fun getRandomAdvice(): Call<AdviceMsg> {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val adviceAPI = retrofit.create(AdviceAPI::class.java)

        return adviceAPI.randomAdvice()
    }

    fun getRandomAdviceWithOkHttp(): Call<AdviceMsg> {

        val logger = HttpLoggingInterceptor()

//        logger.level = HttpLoggingInterceptor.Level.NONE
        logger.level = HttpLoggingInterceptor.Level.BASIC
//        logger.level = HttpLoggingInterceptor.Level.HEADERS
//        logger.level = HttpLoggingInterceptor.Level.BODY

        Log.d(TAG, "logger.level=${logger.level}")

        val ohHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .retryOnConnectionFailure(false)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(ohHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val adviceAPI = retrofit.create(AdviceAPI::class.java)

        return adviceAPI.randomAdvice()
    }
}

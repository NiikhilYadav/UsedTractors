package com.carnot.usedtractors.di

import com.carnot.usedtractors.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClientWithoutHeader(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggingInterceptor)
        }
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        return client.build()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson =
        GsonBuilder().create()



    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder().client(okHttpClient).baseUrl("https://9965c947-aead-4ba9-b647-d7d3bf97c41d.mock.pstmn.io")
            .addConverterFactory(GsonConverterFactory.create(gson))


}
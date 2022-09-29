package com.example.weatherapp.model.remote

import android.util.Log
import com.example.weatherapp.Repository
import com.example.weatherapp.RepositoryImplementation
import com.example.weatherapp.model.remote.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): ApiServer {
        val retrofit = Retrofit.Builder()
            .client(OkHttpClient().newBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiServer::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(apiServer: ApiServer): Repository {
        return RepositoryImplementation(apiServer)
    }
}
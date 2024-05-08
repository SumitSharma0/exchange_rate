package com.example.exchangerateapp.util.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.exchangerateapp.util.appSessionDataStore
import com.example.exchangerateapp.util.ktorClient
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideKtorClient(): HttpClient {
        return ktorClient
    }

    @Provides
    fun provideAppSession(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.appSessionDataStore
    }
}
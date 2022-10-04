package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.UserDao
import com.example.data.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): UserDatabase = UserDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideAlbumDao(appDatabase: UserDatabase): UserDao = appDatabase.userDao()
}
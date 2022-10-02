package com.example.domain.di

import com.example.domain.repository.UsersRepository
import com.example.domain.usecase.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideImagesUseCase(repository: UsersRepository): UsersUseCase {
        return UsersUseCase(repository)
    }
}
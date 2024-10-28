package com.devlog.androidtestcodeexample.di

import com.devlog.androidtestcodeexample.data.model.repository.TodoRepository
import com.devlog.androidtestcodeexample.data.model.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideTodoRepository(): TodoRepository {
        return TodoRepositoryImpl()
    }
}
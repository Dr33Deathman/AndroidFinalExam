package kz.kamran.androidfinalexam.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.kamran.androidfinalexam.data.RemoteRepository
import kz.kamran.androidfinalexam.repo.RemoteRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl):RemoteRepository
}
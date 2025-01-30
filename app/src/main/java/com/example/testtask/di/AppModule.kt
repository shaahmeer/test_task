package com.example.testtask.di



import com.example.core.Repository.Repository
import com.example.core.Repository.RepositoryImpl
import com.example.core.datasource.RemoteDataSource
import com.example.core.usecases.GetOffersUseCase
import com.example.core.usecases.GetVacanciesUseCase
import com.example.datasource.FetchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): FetchApi {
        return retrofit.create(FetchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: FetchApi): RemoteDataSource {
        return RemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource): Repository {
        return RepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetOffersUseCase(repository: Repository): GetOffersUseCase {
        return GetOffersUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetVacanciesUseCase(repository: Repository): GetVacanciesUseCase {
        return GetVacanciesUseCase(repository)
    }
}

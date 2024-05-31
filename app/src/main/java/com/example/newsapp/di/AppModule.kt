package com.example.newsapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.data.local.NewsDatabase
import com.example.data.remote.NewsApi
import com.example.data.repositories_impl.CachedNewsRepositoryImpl
import com.example.data.repositories_impl.GetHeadLinesRepositoryImpl
import com.example.data.repositories_impl.SaveOnBoardingStatusRepositoryImpl
import com.example.domain.repositories.CachedNewsRepository
import com.example.domain.repositories.GetHeadLinesRepository
import com.example.domain.repositories.SaveOnBoardingStatusRepository
import com.example.domain.use_cases.DeleteFavArticlesUseCase
import com.example.domain.use_cases.GetCurrentLangUseCase
import com.example.domain.use_cases.GetFavArticlesUseCase
import com.example.domain.use_cases.GetHeadLinesUseCase
import com.example.domain.use_cases.GetOnBoardingStatusUseCase
import com.example.domain.use_cases.SaveCurrentLangUseCase
import com.example.domain.use_cases.SaveFavArticlesUseCase
import com.example.domain.use_cases.SaveOnBoardingStatusUseCase
import com.example.domain.use_cases_impl.DeleteFavArticlesUseCaseImpl
import com.example.domain.use_cases_impl.GetCurrentLangUseCaseImpl
import com.example.domain.use_cases_impl.GetFavArticlesUseCaseImpl
import com.example.domain.use_cases_impl.GetHeadLinesUseCaseImpl
import com.example.domain.use_cases_impl.GetOnBoardingStatusUseCaseImpl
import com.example.domain.use_cases_impl.SaveCurrentLangUseCaseImpl
import com.example.domain.use_cases_impl.SaveFavArticlesUseCaseImpl
import com.example.domain.use_cases_impl.SaveOnBoardingStatusUseCaseImpl
import com.example.newsapp.BuildConfig
import com.example.newsapp.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        return okHttpBuilder.run {
            if (BuildConfig.DEBUG) addInterceptor(httpLoggingInterceptor)
            build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constant.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)


    @Provides
    @Singleton
    fun saveOnBoardingStatusUseCase(saveOnBoardingStatusRepository: SaveOnBoardingStatusRepository): SaveOnBoardingStatusUseCase =
        SaveOnBoardingStatusUseCaseImpl(saveOnBoardingStatusRepository)

    @Provides
    @Singleton
    fun GetOnBoardingStatusUseCase(saveOnBoardingStatusRepository: SaveOnBoardingStatusRepository): GetOnBoardingStatusUseCase =
        GetOnBoardingStatusUseCaseImpl(saveOnBoardingStatusRepository)

    @Provides
    @Singleton
    fun SaveCurrentLangUseCase(saveOnBoardingStatusRepository: SaveOnBoardingStatusRepository): SaveCurrentLangUseCase =
        SaveCurrentLangUseCaseImpl(saveOnBoardingStatusRepository)

    @Provides
    @Singleton
    fun GetCurrentLangUseCase(saveOnBoardingStatusRepository: SaveOnBoardingStatusRepository): GetCurrentLangUseCase =
        GetCurrentLangUseCaseImpl(saveOnBoardingStatusRepository)

    @Provides
    @Singleton
    fun getHeadLinesUseCase(getHeadLinesRepository: GetHeadLinesRepository,cachedNewsRepository: CachedNewsRepository): GetHeadLinesUseCase =
        GetHeadLinesUseCaseImpl(getHeadLinesRepository,cachedNewsRepository)

    @Provides
    @Singleton
    fun getFavArticlesUseCase(cachedNewsRepository: CachedNewsRepository): GetFavArticlesUseCase =
        GetFavArticlesUseCaseImpl(cachedNewsRepository)

    @Provides
    @Singleton
    fun saveFavArticlesUseCase(cachedNewsRepository: CachedNewsRepository): SaveFavArticlesUseCase =
        SaveFavArticlesUseCaseImpl(cachedNewsRepository)
    @Provides
    @Singleton
    fun deleteFavArticlesUseCase(cachedNewsRepository: CachedNewsRepository): DeleteFavArticlesUseCase =
        DeleteFavArticlesUseCaseImpl(cachedNewsRepository)


    @Provides
    @Singleton
    fun saveOnBoardingStatusRepository(sharedPreferences: SharedPreferences): SaveOnBoardingStatusRepository =
        SaveOnBoardingStatusRepositoryImpl(sharedPreferences)

    @Provides
    @Singleton
    fun getHeadLinesRepository(api: NewsApi): GetHeadLinesRepository =
        GetHeadLinesRepositoryImpl(api)

    @Provides
    @Singleton
    fun getCachedNewsRepository(db: NewsDatabase): CachedNewsRepository =
        CachedNewsRepositoryImpl(db)

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("onBoardingStatus", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): NewsDatabase =
        Room.databaseBuilder(app, NewsDatabase::class.java, "news_database")
            .build()
}
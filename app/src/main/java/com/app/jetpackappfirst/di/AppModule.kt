package com.app.jetpackappfirst.di

import android.app.Application
import androidx.room.Room
import com.app.jetpackappfirst.data.local.NewsDao
import com.app.jetpackappfirst.data.local.NewsDatabase
import com.app.jetpackappfirst.data.local.NewsTypeConvertor
import com.app.jetpackappfirst.data.manager.LocalUserManagerImpl
import com.app.jetpackappfirst.data.remote.NewsApi
import com.app.jetpackappfirst.data.repository.NewsRepositoryImpl
import com.app.jetpackappfirst.domain.manager.LocalUserManager
import com.app.jetpackappfirst.domain.repository.NewsRepository
import com.app.jetpackappfirst.domain.usecases.appEntry.AppEntryUseCases
import com.app.jetpackappfirst.domain.usecases.appEntry.ReadAppEntry
import com.app.jetpackappfirst.domain.usecases.appEntry.SaveAppEntry
import com.app.jetpackappfirst.domain.usecases.news.DeleteArticle
import com.app.jetpackappfirst.domain.usecases.news.GetNews
import com.app.jetpackappfirst.domain.usecases.news.NewsUseCases
import com.app.jetpackappfirst.domain.usecases.news.SearchNews
import com.app.jetpackappfirst.domain.usecases.news.SelectArticle
import com.app.jetpackappfirst.domain.usecases.news.SelectArticles
import com.app.jetpackappfirst.domain.usecases.news.UpsertArticle
import com.app.jetpackappfirst.util.Constants
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
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(okHttpClient: OkHttpClient): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = Constants.NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}
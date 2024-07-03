package com.app.jetpackappfirst.domain.usecases.news

import com.app.jetpackappfirst.domain.model.Article
import com.app.jetpackappfirst.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}
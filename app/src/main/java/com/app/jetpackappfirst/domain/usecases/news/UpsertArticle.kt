package com.app.jetpackappfirst.domain.usecases.news

import com.app.jetpackappfirst.domain.model.Article
import com.app.jetpackappfirst.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}
package com.app.jetpackappfirst.presentation.search

import androidx.paging.PagingData
import com.app.jetpackappfirst.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)

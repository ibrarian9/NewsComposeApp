package com.app.jetpackappfirst.presentation.bookmark

import com.app.jetpackappfirst.domain.model.Article

data class BookmarkState(
    val article: List<Article> = emptyList()
)

package com.app.jetpackappfirst.presentation.details

import com.app.jetpackappfirst.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}
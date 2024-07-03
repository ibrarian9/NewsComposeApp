package com.app.jetpackappfirst.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.jetpackappfirst.domain.model.Article

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
): PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(page = page, source = sources)
            TOTAL_NEWS_COUNT += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title } // Remove Duplicate
            LoadResult.Page(
                data = articles,
                nextKey = if (TOTAL_NEWS_COUNT == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    companion object {
        var TOTAL_NEWS_COUNT = 0
    }

}
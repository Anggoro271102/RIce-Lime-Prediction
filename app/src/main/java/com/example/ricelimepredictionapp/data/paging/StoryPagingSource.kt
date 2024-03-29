package com.example.ricelimepredictionapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ricelimepredictionapp.data.datastore.UserPreferenceDatastore
import com.example.ricelimepredictionapp.data.network.ApiService
import com.example.ricelimepredictionapp.model.ListStoryItem
import kotlinx.coroutines.flow.first

class StoryPagingSource (
    private val apiServicePaging: ApiService,
    private val dataStoreRepository: UserPreferenceDatastore
) : PagingSource<Int, ListStoryItem>() {

    override fun getRefreshKey(state: PagingState<Int, ListStoryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListStoryItem> {
        val position = params.key ?: INITIAL_PAGE_INDEX

        return try {
            val token = dataStoreRepository.getUser().first()
            val userToken = "Bearer ${token.token}"
            val queryParam = HashMap<String, Int>()
            queryParam["page"] = position
            queryParam["size"] = params.loadSize
            queryParam["location"] = 0

            val responseData = apiServicePaging.ListStory(userToken, queryParam)
            LoadResult.Page(
                data = responseData.listStory,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.listStory.isEmpty()) null else position + 1
            )
        } catch (e: Exception) { return LoadResult.Error(e) }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}
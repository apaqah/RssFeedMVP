package com.apaqah.rssfeedmvp

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock



class ResultPresenterTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private var resultFeedPresenter: ResultFeedPresenter? = null
    @Mock
    var mMockContext: Context? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        resultFeedPresenter = mMockContext?.let { ResultFeedPresenter(it) }
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun fetchFeed() {
        resultFeedPresenter?.onSearchFeed("https://www.antaranews.com/rss/top-news")
    }
}
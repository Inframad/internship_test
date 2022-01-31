package com.example.internshiptest.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.entity.RequestError
import com.example.internshiptest.domain.usecase.GetNewsUsecase
import com.example.internshiptest.domain.usecase.UpdateNewsUsecase
import com.example.internshiptest.presentation.state.NewsFragmentState
import com.example.internshiptest.presentation.util.CoroutinesTestRule
import com.example.internshiptest.presentation.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException
import java.time.OffsetDateTime

@ExperimentalCoroutinesApi
class NewsFragmentViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val getNewsUsecase = mockkClass(GetNewsUsecase::class)
    private val updateNewsUsecase = mockkClass(UpdateNewsUsecase::class)

    private val testArticle =
        Article(
            id = 0,
            title = "Title",
            description = "Description",
            date = OffsetDateTime.MAX,
            imageUrl = "https://example.com/image.jpg",
            author = "John Miller"
        )

    private val fakeNews = listOf(testArticle)

    @Test
    fun `WHEN init EXPECT updateNewsUsecase()`() {
        coEvery { getNewsUsecase() } returns flow {
            emit(emptyList())
        }
        coEvery { updateNewsUsecase() } returns Unit

        NewsFragmentViewModel(
            getNewsUsecase,
            updateNewsUsecase
        )

        coroutinesTestRule.testCoroutineScheduler.runCurrent()

        coVerify { updateNewsUsecase() }
    }

    @Test
    fun `WHEN updateNews() EXPECT state is LOADED`() {
        coEvery { getNewsUsecase() } returns flow {
            emit(emptyList())
        }
        coEvery { updateNewsUsecase() } returns Unit

        val viewModel = NewsFragmentViewModel(
            getNewsUsecase,
            updateNewsUsecase
        )
        viewModel.updateNews()
        coroutinesTestRule.testCoroutineScheduler.runCurrent()

        val actual = viewModel.state.getOrAwaitValue()
        val expected = NewsFragmentState.LOADED
        MatcherAssert.assertThat(actual, `is`(expected))
    }

    @Test
    fun `WHEN updateNews() EXPECT updateNewsUsecase()`() {
        coEvery { getNewsUsecase() } returns flow {
            emit(emptyList())
        }
        coEvery { updateNewsUsecase() } returns Unit

        val viewModel = NewsFragmentViewModel(
            getNewsUsecase,
            updateNewsUsecase
        )
        viewModel.updateNews()
        coroutinesTestRule.testCoroutineScheduler.runCurrent()

        coVerify { updateNewsUsecase() }
    }

    @Test
    fun `WHEN init EXPECT news is Flow of fakeNews`() {
        runTest {
            coEvery { getNewsUsecase() } returns flow {
                emit(fakeNews)
            }
            coEvery { updateNewsUsecase() } returns Unit

            val viewModel = NewsFragmentViewModel(
                getNewsUsecase,
                updateNewsUsecase
            )
            coroutinesTestRule.testCoroutineScheduler.runCurrent()

            val actual = viewModel.news.first()
            val expected = fakeNews
            MatcherAssert.assertThat(actual, `is`(expected))
        }
    }

    @Test
    fun `WHEN updateNewsUsecase() throw UnknownHostException EXPECT state is OFFLINE_MODE`() {
        runTest {
            val actual = stateWhenUpdateNewsUsecaseThrowException(UnknownHostException())
            val expected = NewsFragmentState.OFFLINE_MODE
            MatcherAssert.assertThat(actual, `is`(expected))
        }
    }

    @Test
    fun `WHEN updateNewsUsecase() throw RequestError(500) EXPECT state is SERVER_IS_NOT_AVAILABLE`() {
        runTest {
            val actual = stateWhenUpdateNewsUsecaseThrowException(RequestError(500))
            val expected = NewsFragmentState.SERVER_IS_NOT_AVAILABLE
            MatcherAssert.assertThat(actual, `is`(expected))
        }
    }

    @Test
    fun `WHEN updateNewsUsecase() throw RequestError(-1) EXPECT state is UNKNOWN_ERROR`() {
        runTest {
            val actual = stateWhenUpdateNewsUsecaseThrowException(RequestError(-1))
            val expected = NewsFragmentState.UNKNOWN_ERROR
            MatcherAssert.assertThat(actual, `is`(expected))
        }
    }

    @Test
    fun `WHEN updateNewsUsecase() throw Throwable() EXPECT state is UNKNOWN_ERROR`() {
        runTest {
            val actual = stateWhenUpdateNewsUsecaseThrowException(Throwable())
            val expected = NewsFragmentState.UNKNOWN_ERROR
            MatcherAssert.assertThat(actual, `is`(expected))
        }
    }


    private fun stateWhenUpdateNewsUsecaseThrowException(exception: Throwable): NewsFragmentState {
        coEvery { getNewsUsecase() } returns flow {
            emit(emptyList())
        }
        coEvery { updateNewsUsecase() } throws exception

        val viewModel = NewsFragmentViewModel(
            getNewsUsecase,
            updateNewsUsecase
        )
        viewModel.updateNews()
        coroutinesTestRule.testCoroutineScheduler.runCurrent()

        return viewModel.state.getOrAwaitValue()
    }
}
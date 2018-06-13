package com.toolslab.bookshelf.view.bookshelf

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.bookshelf.model.BookViewModel
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class BookshelfAdapterTest {

    companion object {
        private val viewModel1 = BookViewModel("title1", "author1", "description1", "date1", 1)
        private val viewModel2 = BookViewModel("title2", "author2", "description2", "date2", 2, "header text")
        private val VIEW_MODELS = listOf(viewModel1, viewModel2)
    }

    private val mockRowViewHolder: BookshelfAdapter.RowViewHolder = mock()
    private val mockHeaderViewHolder: BookshelfAdapter.HeaderViewHolder = mock()
    private val mockHeaderTextView: TextView = mock()
    private val mockTitleTextView: TextView = mock()
    private val mockAuthorTextView: TextView = mock()
    private val mockDescriptionTextView: TextView = mock()
    private val mockDateTextView: TextView = mock()
    private val mockBackgroundView: View = mock()

    private val underTest = BookshelfAdapter(VIEW_MODELS)

    @Before
    fun setUp() {
        whenever(mockHeaderViewHolder.headerText).thenReturn(mockHeaderTextView)
        whenever(mockRowViewHolder.title).thenReturn(mockTitleTextView)
        whenever(mockRowViewHolder.author).thenReturn(mockAuthorTextView)
        whenever(mockRowViewHolder.description).thenReturn(mockDescriptionTextView)
        whenever(mockRowViewHolder.date).thenReturn(mockDateTextView)
        whenever(mockRowViewHolder.background).thenReturn(mockBackgroundView)
    }

    @Test
    fun onBindRowViewHolder() {
        underTest.onBindViewHolder(mockRowViewHolder, 0)

        verify(mockTitleTextView).text = viewModel1.title
        verify(mockAuthorTextView).text = viewModel1.author
        verify(mockDescriptionTextView).text = viewModel1.description
        verify(mockDateTextView).text = viewModel1.date
        verify(mockBackgroundView).background = any<ColorDrawable>()
    }

    @Test
    fun onBindHeaderViewHolder() {
        underTest.onBindViewHolder(mockHeaderViewHolder, 1)

        verify(mockHeaderTextView).text = viewModel2.headerText
    }

    @Test
    fun getItemCount() {
        val result = underTest.itemCount

        result shouldEqual VIEW_MODELS.size
    }

}

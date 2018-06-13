package com.toolslab.bookshelf.view.bookshelf

import android.support.v7.widget.LinearLayoutManager
import com.nhaarman.mockito_kotlin.*
import com.toolslab.bookshelf.model.BookViewModel
import org.amshove.kluent.shouldEqual
import org.junit.Test

class BookshelfActivityTest {

    private val mockViewModels: List<BookViewModel> = mock()

    private val underTest = BookshelfActivity()

    @Test
    fun initMessagesList() {
        underTest.recyclerView = mock()
        val captor = argumentCaptor<BookshelfAdapter>()
        val expectedItemCount = 1234
        whenever(mockViewModels.size).thenReturn(expectedItemCount)

        underTest.setViewModels(mockViewModels)

        verify(underTest.recyclerView).setHasFixedSize(true)
        verify(underTest.recyclerView).layoutManager = any<LinearLayoutManager>()
        verify(underTest.recyclerView).adapter = captor.capture()
        captor.firstValue.itemCount shouldEqual expectedItemCount
    }

}

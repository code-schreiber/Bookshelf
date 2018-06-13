package com.toolslab.bookshelf.model

import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class BookViewModelTest {

    @Test
    fun isHeader() {
        val viewModel = BookViewModel("", "", "", "", 0, "header text")

        viewModel.isHeader() shouldEqualTo true
    }

    @Test
    fun isNotHeader() {
        val viewModel = BookViewModel("", "", "", "", 0)

        viewModel.isHeader() shouldEqualTo false
    }

}

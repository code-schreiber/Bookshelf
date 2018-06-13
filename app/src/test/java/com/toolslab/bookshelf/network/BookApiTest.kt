package com.toolslab.bookshelf.network

import com.nhaarman.mockito_kotlin.mock
import com.toolslab.bookshelf.network.BookApi.Companion.AUTHORS
import com.toolslab.bookshelf.network.BookApi.Companion.DESCRIPTION
import com.toolslab.bookshelf.network.BookApi.Companion.MAX_DAYS_AGO
import com.toolslab.bookshelf.network.BookApi.Companion.MAX_DESCRIPTION_CUT
import com.toolslab.bookshelf.network.BookApi.Companion.MILLIS_IN_A_DAY
import com.toolslab.bookshelf.network.BookApi.Companion.NR_OF_BOOKS
import com.toolslab.bookshelf.network.BookApi.Companion.TITLES
import com.toolslab.bookshelf.util.ColorGenerator
import com.toolslab.bookshelf.util.ColorGenerator.Companion.MAX_RGB
import org.amshove.kluent.shouldBeGreaterOrEqualTo
import org.amshove.kluent.shouldBeLessOrEqualTo
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.util.*

class BookApiTest {

    private val mockColorGenerator: ColorGenerator = mock()

    private val underTest = BookApi(mockColorGenerator)

    @Test
    fun generateDummyBooks() {
        val result = underTest.generateDummyBooks()

        result.size shouldEqual NR_OF_BOOKS

        for (book in result) {
            TITLES shouldContain book.title
            AUTHORS shouldContain book.author
            DESCRIPTION shouldContain book.description
            book.description.length shouldBeGreaterOrEqualTo MAX_DESCRIPTION_CUT
            book.date shouldBeLessOrEqualTo Date().time
            book.date shouldBeGreaterOrEqualTo Date().time - MAX_DAYS_AGO * MILLIS_IN_A_DAY
            book.color shouldBeGreaterOrEqualTo 0
            book.color shouldBeLessOrEqualTo MAX_RGB
        }
    }

}

package com.toolslab.bookshelf.view.bookshelf

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.bookshelf.db.BookRepository
import com.toolslab.bookshelf.model.Book
import com.toolslab.bookshelf.model.converter.BookToBookModelConverter
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class BookshelfPresenterTest {

    private val mockBookRepository: BookRepository = mock()
    private val mockCompositeDisposable: CompositeDisposable = mock()
    private val mockView: BookshelfContract.View = mock()

    private val underTest = BookshelfPresenter(mockBookRepository, mockCompositeDisposable, BookToBookModelConverter())

    @Before
    fun setUp() {
        whenever(mockBookRepository.getBooks()).thenReturn(Observable.just(BOOKS))

        underTest.bind(mockView)
    }

    @Test
    fun onBound() {
        verify(mockView).setViewModels(any())
        verify(mockCompositeDisposable).add(any())
    }

    @Test
    fun onUnbound() {
        underTest.onUnbound(mockView)

        verify(mockCompositeDisposable).clear()
    }

    @Test
    fun prepareViewModelsWithCorrectOrder() {
        val viewModels = underTest.prepareViewModels(BOOKS)

        viewModels.size shouldEqual 11 // Expect 6 books and 5 sections

        viewModels[0].isHeader() shouldEqual true
        viewModels[1].isHeader() shouldEqual false
        viewModels[1].title shouldEqual "title1"

        viewModels[2].isHeader() shouldEqual true
        viewModels[3].isHeader() shouldEqual false
        viewModels[3].title shouldEqual "title2"

        viewModels[4].isHeader() shouldEqual true
        viewModels[5].isHeader() shouldEqual false
        viewModels[5].title shouldEqual "title3"
        viewModels[6].isHeader() shouldEqual false
        viewModels[6].title shouldEqual "title4"

        viewModels[7].isHeader() shouldEqual true
        viewModels[8].isHeader() shouldEqual false
        viewModels[8].title shouldEqual "title5"

        viewModels[9].isHeader() shouldEqual true
        viewModels[10].isHeader() shouldEqual false
        viewModels[10].title shouldEqual "title6"
    }

    @Test
    fun prepareViewModelsWithCorrectHeaders() {
        val viewModels = underTest.prepareViewModels(BOOKS)

        viewModels.size shouldEqual 11 // Expect 6 books and 5 sections

        viewModels[0].headerText shouldEqual "Monday, January 1, 2018 - Sunday, January 7, 2018"
        viewModels[0].date shouldEqual ""
        viewModels[1].headerText shouldEqual ""
        viewModels[1].date shouldEqual "Jan 1, 2018"

        viewModels[2].headerText shouldEqual "Monday, June 4, 2018 - Sunday, June 10, 2018"
        viewModels[2].date shouldEqual ""
        viewModels[3].headerText shouldEqual ""
        viewModels[3].date shouldEqual "Jun 10, 2018"

        viewModels[4].headerText shouldEqual "Monday, June 11, 2018 - Sunday, June 17, 2018"
        viewModels[4].date shouldEqual ""
        viewModels[5].headerText shouldEqual ""
        viewModels[5].date shouldEqual "Jun 11, 2018"
        viewModels[6].headerText shouldEqual ""
        viewModels[6].date shouldEqual "Jun 17, 2018"

        viewModels[7].headerText shouldEqual "Monday, June 18, 2018 - Sunday, June 24, 2018"
        viewModels[7].date shouldEqual ""
        viewModels[8].headerText shouldEqual ""
        viewModels[8].date shouldEqual "Jun 18, 2018"

        viewModels[9].headerText shouldEqual "Monday, December 31, 2018 - Sunday, January 6, 2019"
        viewModels[9].date shouldEqual ""
        viewModels[10].headerText shouldEqual ""
        viewModels[10].date shouldEqual "Dec 31, 2018"
    }

    companion object {
        private const val date1 = 1514761200000L // Monday Jan 1 2018
        private const val date2 = 1528581600000L // Sunday Jun 10 2018
        private const val date3 = 1528668000000L // Monday Jun 11 2018
        private const val date4 = 1529186400000L // Sunday Jun 17 2018
        private const val date5 = 1529272800000L // Monday Jun 18 2018
        private const val date6 = 1546210800000L // Monday Dec 31 2018

        private val BOOKS = listOf(
                Book("title1", "author1", "description1", date1, 1),
                Book("title2", "author2", "description2", date2, 2),
                Book("title3", "author3", "description3", date3, 3),
                Book("title4", "author4", "description4", date4, 4),
                Book("title5", "author5", "description5", date5, 5),
                Book("title6", "author6", "description6", date6, 6))
    }

}

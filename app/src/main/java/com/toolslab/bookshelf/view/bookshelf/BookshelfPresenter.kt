package com.toolslab.bookshelf.view.bookshelf

import android.support.annotation.VisibleForTesting
import com.toolslab.bookshelf.db.BookRepository
import com.toolslab.bookshelf.model.Book
import com.toolslab.bookshelf.model.BookViewModel
import com.toolslab.bookshelf.model.converter.BookToBookModelConverter
import com.toolslab.bookshelf.mvp.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class BookshelfPresenter @Inject constructor(private val bookRepository: BookRepository,
                                             private val compositeDisposable: CompositeDisposable,
                                             private val bookToBookModelConverter: BookToBookModelConverter)
    : BasePresenter<BookshelfContract.View>(), BookshelfContract.Presenter {

    override fun onBound(view: BookshelfContract.View) {
        compositeDisposable.add(bookRepository.getBooks()
                .subscribe {
                    val viewModels = prepareViewModels(it.sorted())
                    view.setViewModels(viewModels)
                })
    }

    override fun onUnbound(view: BookshelfContract.View) {
        compositeDisposable.clear()
    }

    @VisibleForTesting
    fun prepareViewModels(books: List<Book>): List<BookViewModel> {
        var lastWeek = -1
        val viewModels = mutableListOf<BookViewModel>()
        for (book in books) {
            val currentWeek = extractWeek(book.date)
            if (lastWeek != currentWeek) {
                // Add a header between weeks
                viewModels.add(createHeader(book.formattedWeek()))
            }
            lastWeek = currentWeek
            viewModels.add(bookToBookModelConverter.convert(book))
        }
        return viewModels
    }

    private fun extractWeek(date: Long): Int {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        val weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR)
        // We want sundays counting as last day instead of first day of the week
        return if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) weekOfYear - 1 else weekOfYear
    }

    private fun createHeader(formattedWeek: String) = BookViewModel("", "", "", "", 0, formattedWeek)

}

package com.toolslab.bookshelf.db

import com.toolslab.bookshelf.model.Book
import com.toolslab.bookshelf.network.BookApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class BookRepository @Inject constructor() {

    @Inject
    internal lateinit var bookApi: BookApi

    @Inject
    internal lateinit var bookDao: BookDao


    fun getBooks(): Observable<List<Book>> {
        // First deliver to the subscribers the data from the database,
        // and secondly the data from the API.
        return Observable.concatArray(
                getBooksFromDatabase()
                        .mergeWith(getBooksFromApi()))
    }

    fun getBooksFromDatabase(): Observable<List<Book>> {
        return bookDao.getBooks()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} books from database...")
                }
    }

    fun getBooksFromApi(): Observable<List<Book>> {
        return bookApi.getBooks()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} books from API...")
                    storeBooksInDatabase(it)
                }
    }

    fun storeBooksInDatabase(books: List<Book>) {
        // Async operation to store the data
        Observable.fromCallable { bookDao.saveBooks(books) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${books.size} books from API in database...")
                }
    }

}

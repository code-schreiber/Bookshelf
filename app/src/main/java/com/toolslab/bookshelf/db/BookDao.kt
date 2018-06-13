package com.toolslab.bookshelf.db

import android.support.annotation.CheckResult
import com.toolslab.bookshelf.model.Book
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class BookDao @Inject constructor() {

    @Inject
    internal lateinit var realmWrapper: RealmWrapper

    @CheckResult
    fun getBooks(): Observable<List<Book>> {
        return realmWrapper.getDefaultInstance().run {
            this.where(Book::class.java).findAllAsync()
                    .asFlowable()
                    .toObservable()
                    .map {
                        this.copyFromRealm(it)
                    }
                    .doOnDispose {
                        // Close database when observable is not needed anymore
                        this.close()
                    }
                    .doOnError {
                        Timber.e(it, "Error in BookDao.getBooks()")
                    }
        }
    }

    fun saveBooks(books: List<Book>) {
        realmWrapper.getDefaultInstance().run {
            // Persist your data in a transaction
            this.executeTransaction {
                // Using executeTransaction with a lambda reduces code size
                // and makes it impossible to forget to commit the transaction.
                it.copyToRealm(books)
            }
            // Close database after saving
            this.close()
        }
    }

}

package com.toolslab.bookshelf.view.bookshelf

import com.toolslab.bookshelf.db.BookRepository
import com.toolslab.bookshelf.model.converter.BookToBookModelConverter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class BookshelfModule {

    @Provides
    fun providePresenter(bookRepository: BookRepository,
                         compositeDisposable: CompositeDisposable,
                         bookToBookModelConverter: BookToBookModelConverter): BookshelfContract.Presenter =
            BookshelfPresenter(bookRepository, compositeDisposable, bookToBookModelConverter)

}

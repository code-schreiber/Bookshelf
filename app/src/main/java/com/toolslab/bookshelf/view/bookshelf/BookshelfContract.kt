package com.toolslab.bookshelf.view.bookshelf

import com.toolslab.bookshelf.model.BookViewModel
import com.toolslab.bookshelf.mvp.BaseView
import com.toolslab.bookshelf.mvp.MvpPresenter

interface BookshelfContract {

    interface Presenter : MvpPresenter<View>

    interface View : BaseView {

        fun setViewModels(viewModels: List<BookViewModel>)

    }
}

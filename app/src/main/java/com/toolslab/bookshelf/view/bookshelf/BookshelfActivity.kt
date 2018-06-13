package com.toolslab.bookshelf.view.bookshelf

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.toolslab.bookshelf.R
import com.toolslab.bookshelf.model.BookViewModel
import com.toolslab.bookshelf.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_bookshelf.*
import javax.inject.Inject

class BookshelfActivity : BaseActivity(), BookshelfContract.View {

    @VisibleForTesting
    internal lateinit var recyclerView: RecyclerView

    @Inject
    internal lateinit var presenter: BookshelfContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookshelf)
        recyclerView = activity_bookshelf_recycleview
        presenter.bind(this)
    }

    override fun onDestroy() {
        presenter.unbind(this)
        super.onDestroy()
    }

    override fun setViewModels(viewModels: List<BookViewModel>) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BookshelfAdapter(viewModels)
    }

}

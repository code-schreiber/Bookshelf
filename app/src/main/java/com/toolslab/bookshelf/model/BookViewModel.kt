package com.toolslab.bookshelf.model

class BookViewModel(internal val title: String,
                    internal val author: String,
                    internal val description: String,
                    internal val date: String,
                    internal val color: Int,
                    internal var headerText: String = "") {

    fun isHeader() = !headerText.isEmpty()

}

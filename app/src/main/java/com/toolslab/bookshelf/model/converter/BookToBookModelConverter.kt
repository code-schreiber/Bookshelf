package com.toolslab.bookshelf.model.converter

import com.toolslab.bookshelf.model.Book
import com.toolslab.bookshelf.model.BookViewModel
import javax.inject.Inject

class BookToBookModelConverter @Inject constructor() : ViewModelConverter<Book, BookViewModel> {

    override fun convert(model: Book): BookViewModel {
        return BookViewModel(model.title, model.author, model.description, model.formattedDate(), model.color)
    }

}

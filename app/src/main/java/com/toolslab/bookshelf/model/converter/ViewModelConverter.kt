package com.toolslab.bookshelf.model.converter

interface ViewModelConverter<in Model, out ViewModel> {

    fun convert(model: Model): ViewModel
}

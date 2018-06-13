package com.toolslab.bookshelf.di

import com.toolslab.bookshelf.view.bookshelf.BookshelfActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @ContributesAndroidInjector
    abstract fun bookshelfActivity(): BookshelfActivity

}

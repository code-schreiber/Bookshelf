package com.toolslab.bookshelf.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AppModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}

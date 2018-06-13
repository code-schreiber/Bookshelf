package com.toolslab.bookshelf.di

import android.app.Application
import com.toolslab.bookshelf.Bookshelf
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class
        ]
)
interface AppComponent : AndroidInjector<Bookshelf> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder

        fun build(): AppComponent
    }
}

package com.toolslab.bookshelf

import com.toolslab.bookshelf.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import timber.log.Timber
import timber.log.Timber.DebugTree


class Bookshelf : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
                .builder()
                .create(this)
                .build()
    }

}

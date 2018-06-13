package com.toolslab.bookshelf.db

import io.realm.Realm
import javax.inject.Inject

/**
 * Class that makes mocking easier when using [Realm.getDefaultInstance].
 */
class RealmWrapper @Inject constructor() {

    internal fun getDefaultInstance() = Realm.getDefaultInstance()

}

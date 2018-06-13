package com.toolslab.bookshelf.model

import io.realm.RealmObject
import java.text.DateFormat
import java.util.*

open class Book(internal var title: String,
                internal var author: String,
                internal var description: String,
                internal var date: Long,
                internal var color: Int)
    : RealmObject(), Comparable<Book> {

    fun formattedDate(): String = DateFormat.getDateInstance(DateFormat.MEDIUM).format(Date(date))

    fun formattedWeek(): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        var dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2
        if (dayOfWeek == -1) dayOfWeek = 6 // Take sunday as the last day of the week
        val startOfWeek = Date(date - (dayOfWeek * MILLIS_IN_A_DAY))
        val endOfWeek = Date(date + ((6 - dayOfWeek) * MILLIS_IN_A_DAY))
        val dateTimeInstance = DateFormat.getDateInstance(DateFormat.FULL)
        return "${dateTimeInstance.format(startOfWeek)} - ${dateTimeInstance.format(endOfWeek)}"
    }

    // Constructor needed for Realm
    constructor() : this("", "", "", 0, 0)

    override fun compareTo(other: Book) = compareValuesBy(other, this, { it.date })

    companion object {
        const val MILLIS_IN_A_DAY = 24L * 60L * 60L * 1000L
    }

}

package com.toolslab.bookshelf.model

import org.amshove.kluent.shouldEqual
import org.junit.Test

class BookTest {

    private val underTest = Book()

    @Test
    fun formattedDate() {
        underTest.date = 1528890347384L // Wed Jun 13 13:46:09 CEST 2018

        val week = underTest.formattedDate()

        week shouldEqual "Jun 13, 2018"
    }

    @Test
    fun formattedWeek() {
        underTest.date = 1528890347384L // Wed Jun 13 13:46:09 CEST 2018

        val week = underTest.formattedWeek()

        week shouldEqual "Monday, June 11, 2018 - Sunday, June 17, 2018"
    }

    @Test
    fun formattedWeekOfMonday() {
        underTest.date = 1528668000000L // Mon Jun 11 00:00:00 CEST 2018

        val week = underTest.formattedWeek()

        week shouldEqual "Monday, June 11, 2018 - Sunday, June 17, 2018"
    }

    @Test
    fun formattedWeekOfSunday() {
        underTest.date = 1528581600000L // Sun Jun 10 00:00:00 CEST 2018"

        val week = underTest.formattedWeek()

        week shouldEqual "Monday, June 4, 2018 - Sunday, June 10, 2018"
    }

}

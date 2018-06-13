package com.toolslab.bookshelf.network

import android.support.annotation.VisibleForTesting
import com.toolslab.bookshelf.model.Book
import com.toolslab.bookshelf.util.ColorGenerator
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class BookApi @Inject constructor(private val colorGenerator: ColorGenerator) {

    fun getBooks(): Observable<List<Book>> {
        // Return dummy data
        return Observable.fromCallable { generateDummyBooks() }
    }

    @VisibleForTesting
    fun generateDummyBooks(): List<Book> {
        val random = Random()
        val books = mutableListOf<Book>()
        for (i in 1..NR_OF_BOOKS) {
            val title = generateTitle(random)
            val author = generateAuthor(random)
            val description = generateDescription(random)
            val date = generateDate(random)
            val color = generateColor(random)
            books.add(Book(title, author, description, date, color))
        }
        return books
    }

    private fun generateTitle(random: Random) = TITLES[random.nextInt(TITLES.size)]

    private fun generateAuthor(random: Random) = AUTHORS[random.nextInt(AUTHORS.size)]

    private fun generateDescription(random: Random): String {
        val end = DESCRIPTION.length
        val start = random.nextInt(DESCRIPTION.length - MAX_DESCRIPTION_CUT)
        return DESCRIPTION.substring(start, end).trim()
    }

    private fun generateDate(random: Random) = Date().time - (random.nextInt(MAX_DAYS_AGO) * MILLIS_IN_A_DAY)

    private fun generateColor(random: Random) = colorGenerator.generate(random)

    companion object {

        @VisibleForTesting
        const val NR_OF_BOOKS = 25

        @VisibleForTesting
        const val MAX_DAYS_AGO = 30 * 1

        @VisibleForTesting
        const val MILLIS_IN_A_DAY = 24L * 60L * 60L * 1000L

        @VisibleForTesting
        val AUTHORS = listOf(
                "Mary Flores",
                "Juan Scott",
                "Wanda Perez",
                "Emily Adams",
                "James Butler",
                "Brandon Cox",
                "Angela Johnson",
                "Kelly Wright",
                "Theresa Phillips",
                "Maria Bell",
                "Marie Campbell",
                "Ruby Lee",
                "Stephen Davis",
                "Phillip Robinson",
                "Nicole Bryant",
                "John Martinez",
                "Ruth Ramirez",
                "Willie Thomas",
                "Joe Perry",
                "Walter Ward",
                "Bonnie Morris",
                "Brenda Clark",
                "Frank Parker",
                "Christopher Hughes",
                "Joshua Griffin")

        @VisibleForTesting
        val TITLES = listOf(
                "37 People Who Failed So Spectacularly They Almost Won",
                "Our Favorite Celeb Based On Gossip Girl",
                "22 Pictures Of Your Dreams",
                "Boston’s Subway Won’t See Full Service For At Least 30 Days",
                "This Quiz Will Determine What Song Is Your Personal Catwalk Jam",
                "21 Delicious Holiday Snacks That’ll Change Your Life",
                "12 No-electricity Meals",
                "19 Most Swoonworthy Lyrics That Prove Halloween",
                "17 Perfect For A Run",
                "The Liberals Will Form A Majority Government In Canada",
                "21 Things That Will Jump Start Your Mornings",
                "17 Beauty Rules That Need To Sit The Hell Down",
                "15 Crush That Rage Into A Diamond",
                "26 Pregnant Women Who Totally Slayed Halloween",
                "Early ’00s Outfits You Won’t Believe Victoria Beckham Ever Wore",
                "18 Tinder Messages That Should Do You Get An Incredible Mardi Gras",
                "15 Gifs That Need To Adopt",
                "66 Songs That Define The ’90s…for Better Or Worse",
                "13 Times Celebs Totally Surprised Us In The 00s Look",
                "The Things You Need To Know About Rio De Janeiro’s Carnival",
                "21 Unexpected Gifts That Will Leave You Drooling",
                "The 33 Most Beautiful Abandoned Places In The World",
                "33 Clever Diy Projects",
                "Signs You’re The Cady Heron Of Your Friend Group",
                "This Dad Did The Math And Figured Out The Stay At Home Moms Should Earn $73960 A Year")

        @VisibleForTesting
        const val DESCRIPTION = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."

        @VisibleForTesting
        const val MAX_DESCRIPTION_CUT = DESCRIPTION.length - 155
    }

}

package aoc

import java.io.File
import java.io.InputStream


enum class Day(val value: String) {
    One("day1"), Two("day2"), Three("day3"), Four("day4"), Five("day5")
}

object Reader {
    fun getInput(day: Day, test: Boolean = false) : String {
        val fileName = day.value + if(test) "/test.txt" else "/input.txt"
        val inputStream: InputStream = File(fileName).inputStream()
        return inputStream.bufferedReader().use { it.readText() }
    }

}
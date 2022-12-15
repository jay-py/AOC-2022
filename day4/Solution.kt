import java.io.File
import java.io.InputStream

fun main() {
    part1(readInput())
    part2(readInput())
}

private fun readInput(test: Boolean = false) : String {
    val fileName = if(test) "test.txt" else "input.txt"
    val inputStream: InputStream = File(fileName).inputStream()
    return inputStream.bufferedReader().use { it.readText() }
}

private fun part1(input: String) {
    val res = input.split("\n")
                    .filterNot{ it.isBlank() }
                    .map { it.split(",") }
                    .map { it.map { it.split("-").map { it.toInt() }} }
                    .map { it.map {it[0] .. it[1] } }
                    .map { isFullyOverlaping(it[0], it[1]) }
                    .filter { it }
                    .size
    println(">> part1: " + res)
}

private fun part2(input: String) {
    val res = input.split("\n")
                    .filterNot{ it.isBlank() }
                    .map { it.split(",") }
                    .map { it.map { it.split("-").map { it.toInt() }} }
                    .map { it.map { it[0] .. it[1] } }
                    .map { isPartiallyOverlaping(it[0], it[1]) }
                    .filter { it }
                    .size
    println(">> part2: " + res)
}

private fun isFullyOverlaping(range1: IntRange, range2: IntRange) : Boolean {
    return if(range1.intersect(range2) == range1.toSet()) true
    else (range2.intersect(range1) == range2.toSet())
}

private fun isPartiallyOverlaping(range1: IntRange, range2: IntRange) : Boolean {
    return if (range1.intersect(range2).size > 0) true
    else (range2.intersect(range1).size > 0)
}
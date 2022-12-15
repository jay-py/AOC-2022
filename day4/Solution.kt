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

private fun isFullyOverlaping(a: IntRange, b: IntRange) : Boolean {
    return a.intersect(b) in listOf(a.toSet(), b.toSet())
}

private fun isPartiallyOverlaping(a: IntRange, b: IntRange) : Boolean {
    return a.intersect(b).size > 0
}
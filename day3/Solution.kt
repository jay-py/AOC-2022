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
                    .filterNot { it.isBlank() }
                    .map { listOf(it.take(it.length / 2), it.drop(it.length / 2)) }
                    .map { priorities().first { (c, _) -> 
                        it.all { c in it }}.second }
                    .sum()
    println(">> part1: " + res)
}

private fun part2(input: String) {
    val res = "([a-z, A-Z]+\n){3}".toRegex()
                    .findAll(input)
                    .map { it.groupValues.joinToString() }
                    .map { it.split("\n").filterNot { it.isBlank() }.dropLast(1).toList() }
                    .map { priorities().first { (c, _) -> 
                        it.all { c in it }}.second }
                    .sum()
    println(">> part2: " + res)
}

private fun priorities() : List<Pair<Char, Int>> {
    var res = ArrayList<Pair<Char, Int>>()
    val range = (('a'..'z').toMutableList().apply { addAll('A'..'Z') })
    for ((index, char) in range.withIndex()) {
        res.add(Pair(char, index + 1))
    }
    return res
}
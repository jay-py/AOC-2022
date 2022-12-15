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
                    .map { Pair(it.take(it.length / 2).toList(), it.drop(it.length / 2).toList() ) }
                    .map { valueFor((it.first.intersect(it.second)).first()) }
                    .sum()
    println(">> part1: " + res)
}

private fun part2(input: String) {
    val res = input.split("\n")
                    .filterNot { it.isBlank() }
                    .chunked(3)
                    .map { it.map { it.toList() } }
                    .map { valueFor(it[0].intersect(it[1].intersect(it[2])).first()) }
                    .sum()
    println(">> part2: " + res)
}

private fun valueFor(char: Char): Int {
    return char.code - if (char.isLowerCase()) 96
    else 38
}

/*
>> part1: 8109
>> part2: 2738


>> part1: 157
>> part2: 70

*/
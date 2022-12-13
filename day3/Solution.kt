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
                    .filter { !it.isBlank() }
                    .map { Pair(it.take(it.length / 2), it.drop(it.length / 2)) }
                    .map { priorities().mapNotNull { (c, i) -> 
                        if (c in it.first && c in it.second) i 
                        else { null }}[0] }
                    .sum()
    println(">> part1: " + res)
}

private fun part2(input: String) {
    val res = "([a-z, A-Z]+\n){3}".toRegex()
                    .findAll(input)
                    .map { it.groupValues.joinToString() }
                    .map { it.split("\n").filter { !it.isBlank() }.dropLast(1) }
                    .map { it.toList() }
                    .map { priorities().mapNotNull { (c, i) -> 
                        if (it.all { c in it }) i 
                        else { null }}[0] }
                    .sum()
    println(">> part2: " + res)
}

private fun priorities() : List<Pair<Char, Int>> {
    var res = ArrayList<Pair<Char, Int>>()
    for ((i,c) in ('a'..'z').withIndex()) {
        res.add(Pair(c, i + 1))
    }
    for ((i,c) in ('A'..'Z').withIndex()) {
        res.add(Pair(c, i + 27))
    }
    return res
}
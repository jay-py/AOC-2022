import java.io.File
import java.io.InputStream

fun main() {
    part1(readInput(true))
    part2(readInput(true))
}

private fun readInput(test: Boolean = false) : String {
    val fileName = if(test) "test.txt" else "input.txt"
    val inputStream: InputStream = File(fileName).inputStream()
    return inputStream.bufferedReader().use { it.readText() }
}

private fun part1(input: String) {
    val res = input.split("\n\n")
                .map {
                    it.split("\n")
                        .filter {
                            !it.isBlank()
                        }    
                        .map {
                            it.toInt()
                        }
                        .sum()    
                }
                .maxOrNull()
    println(">> part1: " + res)
}

private fun part2(input: String) {
    val res = input.split("\n\n")
                .map {
                    it.split("\n")
                        .filter {
                            !it.isBlank()
                        }    
                        .map {
                            it.toInt()
                        }
                        .sum()    
                }
                .sortedDescending()
                .take(3)
                .sum()
    println(">> part2: " + res)
}

import java.io.File
import java.io.InputStream

fun main() {
    println(readInput(true))
    println(Shape.Rock)
}

private fun readInput(test: Boolean = false) : String {
    val fileName = if(test) "test.txt" else "input.txt"
    val inputStream: InputStream = File(fileName).inputStream()
    return inputStream.bufferedReader().use { it.readText() }
}

private enum class Shape {
    Rock, Paper, Scissors
}


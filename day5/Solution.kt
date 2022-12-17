import java.io.File
import java.io.InputStream
import java.util.ArrayDeque

fun main() {
    part1(readInput(true))
    //part2(readInput())
}

private fun readInput(test: Boolean = false) : String {
    val fileName = if(test) "test.txt" else "input.txt"
    val inputStream: InputStream = File(fileName).inputStream()
    return inputStream.bufferedReader().use { it.readText() }
}

private fun part1(input: String) {
    val (stacks, moves) = stacksAndMoves(input)
    moves.forEach {
        val amount = it[0]
        val from = it[1] - 1
        val to = it[2] - 1
        repeat(amount) {
            stacks[to].push( stacks[from].pop() )
        }
    }
    stacks.forEach{print(it.peek())}
}

private fun part2(input: String) {
    val res = input
    println(">> part2: " + res)
}

private fun stacksAndMoves(input: String) : Pair<MutableList<ArrayDeque<String>>, List<List<Int>>> {
    // parse initial stacks
    val stackReg = "(\\s{3})?(\\[\\S\\])?\\s{0,1}".toRegex()
    val stackLines = input.split("\n")
                    .filterNot { "move" in it }
                    .dropLast(2)
                    .map {
                        stackReg.findAll(it)
                        .map { it.value }
                     }
                     .map {
                        it.toList()
                        .dropLast(1)
                     }

    val columnsCount = stackLines.size - 1
    val rowsCount = stackLines.first().size - 1
    val stacks = mutableListOf<ArrayDeque<String>>()

    for (row in 0..rowsCount) {
        var stack = ArrayDeque<String>()
        for (column in 0..columnsCount) {
            val selection = stackLines[columnsCount - column][row]
            if (Regex("\\[[A-Z]\\]").matches(selection.trim())) {
                stack.push (
                    selection
                        .filterNot { (it in listOf('[', ']', ' ')) })
            }
        }
        stacks.add(stack)
    }
    // parse moves
    val movesLines = input.split("\n").filter {"move" in it}
    val moves = mutableListOf<List<Int>>()

    for (line in movesLines) {
        moves.add(line.split(" ")
                .filterNot { it in listOf("move", "from", "to")}
                .map{ it.toInt() })
    }
    // result
    return Pair(stacks, moves)
}
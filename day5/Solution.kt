import java.io.File
import java.io.InputStream
import java.util.ArrayDeque

fun main() {
    part1(readInput())
    //part2(readInput())
}

private fun readInput(test: Boolean = false) : String {
    val fileName = if(test) "test.txt" else "input.txt"
    val inputStream: InputStream = File(fileName).inputStream()
    return inputStream.bufferedReader().use { it.readText() }
}

private fun part1(input: String) {
    val stacks = getInitialStack(input)
    val moves = getMoves(input)
    for (move in moves) {
        val amount = move[0]
        val from = move[1] - 1
        val to = move[2] - 1
        (0..amount-1).forEach {
            val s = stacks[from].pop()
            stacks[to].push(s)
        }
    }
    stacks.forEach{print(it.peek())}
}

private fun part2(input: String) {
    val res = input
    println(">> part2: " + res)
}

private fun getInitialStack(input: String) : MutableList<ArrayDeque<String>> {
    val reg = "(\\s{3})?(\\[\\S\\])?\\s{0,1}".toRegex()
    val lines = input.split("\n").filterNot { !it.contains("[") || it.contains("move") || it.isBlank()}
                    .map { 
                      reg.findAll(it).map { it.value }
                     }
    
    val rows = lines.map {
        it.toList().dropLast(1)
     }

    val x  = rows.size-1
    val stacks = mutableListOf<ArrayDeque<String>>()
    for (i in 0..rows[0].size-1) {
        var l = ArrayDeque<String>()
        for (j in 0..rows.size-1) {
            val m = rows[x-j][i].replace("[","").replace("]","").trim()
            if (!m.isBlank()) {
                l.push(m)
            }
        }
        stacks.add(l)
    }
    return stacks
}

private fun getMoves(input: String) : List<List<Int>> {
    val res = input.split("\n").filter {"move" in it}
    val li = mutableListOf<List<Int>>()

    for (r in res) {
        li.add(r.split(" ").filterNot{ it in listOf("move", "from", "to")}.map{it.toInt()}  )//toCharArray().filter { it.isDigit() }.map {it.digitToInt()} )
    }
    return li
}

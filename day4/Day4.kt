package aoc

class Day4() {

    init {
        println("Day4")
        val input = Reader.getInput(Day.Four)
        part1(input)
        part2(input)
        println("")
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

    companion object {
        private fun isFullyOverlaping(a: IntRange, b: IntRange) : Boolean {
            return a.intersect(b) in listOf(a.toSet(), b.toSet())
        }
        
        private fun isPartiallyOverlaping(a: IntRange, b: IntRange) : Boolean {
            return a.intersect(b).size > 0
        }    
    }
}

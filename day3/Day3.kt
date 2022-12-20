package aoc

class Day3() {

    init {
        println("Day3")
        val input = Reader.getInput(Day.Three)
        part1(input)
        part2(input)
        println("")
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
}

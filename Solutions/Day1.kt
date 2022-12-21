package aoc

class Day1() {

    init {
        println("Day1")
        val input = Reader.getInput(Day.One)
        part1(input)
        part2(input)
        println("")
    }
    
    private fun part1(input: String) {
        val res = input.split("\n\n")
                        .map {
                            it.split("\n")
                                .filterNot { it.isBlank() }    
                                .map { it.toInt() }
                                .sum()    
                        }
                        .maxOrNull()
        println(">> part1: " + res)
    }
    
    private fun part2(input: String) {
        val res = input.split("\n\n")
                        .map {
                            it.split("\n")
                                .filterNot { it.isBlank() }    
                                .map { it.toInt() }
                                .sum()    
                        }
                        .sortedDescending()
                        .take(3)
                        .sum()
        println(">> part2: " + res)
    }
}

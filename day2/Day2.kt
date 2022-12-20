package aoc

class Day2() {

    init {
        println("Day2")
        val input = Reader.getInput(Day.Two)
        part1(input)
        part2(input)
        println("")
    }

    private fun part1(input: String) {
        val res = input.split("\n")
                        .filterNot { it.isBlank() }
                        .map { it.split(" ") }
                        .map { Pair(Shape.decode(it[0]), Shape.decode(it[1]))}    
                        .map { Shape.score(it.first, it.second) }
                        .sum()
        println(">> part1: " + res)
    }
    
    private fun part2(input: String) {
        val res = input.split("\n")
                        .filterNot { it.isBlank() }
                        .map { it.split(" ") }
                        .map { Pair(Shape.decode(it[0]), Move.decode(it[1]))}    
                        .map { Shape.score(it.first, Shape.forMove(it.first, it.second)) }
                        .sum()
        println(">> part2: " + res)
    }
    
    companion object {
        private enum class Move {
            Win, Draw, Lose;
            
            companion object {
                fun decode(str: String): Move {
                    when (str.lowercase()) {
                        "x"   -> return Lose
                        "y"   -> return Draw
                        else  -> return Win
                    }    
                }
            }
        }
        
        private enum class Shape {
            Rock, Paper, Scissors;
        
            companion object {
                fun decode(str: String): Shape {
                    when (str.lowercase()) {
                        "a" , "x"   -> return Rock
                        "b" , "y"   -> return Paper
                        else        -> return Scissors
                    }
                }
        
        
                fun score(opponent: Shape, me: Shape): Int {
                    val resDict = mutableMapOf<Shape, MutableMap<Shape, Int>>()
                    resDict[Rock] = mutableMapOf(Paper to 1, Rock to 4, Scissors to 7)
                    resDict[Paper] = mutableMapOf(Scissors to 2, Paper to 5, Rock to 8)
                    resDict[Scissors] = mutableMapOf(Rock to 3, Scissors to 6, Paper to 9)
                    return resDict[me]!![opponent]!!
                }
        
                fun forMove(opponent: Shape, move: Move) : Shape {
                    val resDict = mutableMapOf<Shape, MutableMap<Move, Shape>>()
                    resDict[Rock] = mutableMapOf(Move.Lose to Scissors, Move.Draw to Rock, Move.Win to Paper)
                    resDict[Paper] = mutableMapOf(Move.Lose to Rock, Move.Draw to Paper, Move.Win to Scissors)
                    resDict[Scissors] = mutableMapOf(Move.Lose to Paper, Move.Draw to Scissors, Move.Win to Rock)
                    return resDict[opponent]!![move]!!
                }
            } 
        }    
    }   
}
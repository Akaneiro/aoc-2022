import Shape.Scissors.drawShape
import Shape.Scissors.losingShape
import Shape.Scissors.winningShape
import java.lang.Exception

fun main() {

    fun getShapesList(input: List<String>) =
        input.map {
            val inputInline = it.split(" ", limit = 2)
            val opponent = inputInline.first()
            val mine = inputInline.last()

            opponent to mine
        }

    fun part1(input: List<String>): Int {
        val shapesList =
            getShapesList(input).map { Shape.parseExactShape(it.first) to Shape.parseExactShape(it.second) }
        return shapesList.map { (opponent, mine) ->
            mine.score + mine.scoreAccordingToOpponentShape(opponent)
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val shapesList =
            getShapesList(input).map {
                val opponentShape = Shape.parseExactShape(it.first)
               opponentShape to Shape.parseShapeByWinningStrategy(it.second, opponentShape)
            }
        return shapesList.map { (opponent, mine) ->
            mine.score + mine.scoreAccordingToOpponentShape(opponent)
        }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

sealed class Shape(val score: Int) {

    object Rock : Shape(1)

    object Paper : Shape(2)

    object Scissors : Shape(3)

    companion object {
        fun parseExactShape(char: String) =
            when (char) {
                "A", "X" -> Rock
                "B", "Y" -> Paper
                "C", "Z" -> Scissors
                else -> throw Exception("unknown shape")
            }

        fun parseShapeByWinningStrategy(char: String, opponentShape: Shape) =
            when (char) {
                "X" -> losingShape(opponentShape)
                "Y" -> drawShape(opponentShape)
                "Z" -> winningShape(opponentShape)
                else -> throw Exception("unknown shape")
            }
    }

    fun losingShape(opponentShape: Shape) =
        when (opponentShape) {
            Paper -> Rock
            Rock -> Scissors
            Scissors -> Paper
        }

    fun winningShape(opponentShape: Shape) =
        when (opponentShape) {
            Paper -> Scissors
            Rock -> Paper
            Scissors -> Rock
        }

    fun drawShape(opponentShape: Shape) = opponentShape

    fun scoreAccordingToOpponentShape(opponentShape: Shape): Int = when {
        this == losingShape(opponentShape) -> 0
        this == winningShape(opponentShape)-> 6
        this == drawShape(opponentShape) -> 3
        else -> throw Exception("strange situation...")
    }
}

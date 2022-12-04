fun main() {

    fun List<String>.makeIntervals() =
        this
            .map {// convert to list of intervals like listOf([2,3,4], [6,7,8])
                it.split(",")
                    .map {
                        val values = it.split("-")
                        val intRange = values.first().toInt()..values.last().toInt()
                        val array = intRange.toList()
                        array
                    }
            }
            .map { it.first() to it.last() } // make pairs like ([2,3,4] to [6,7,8])

    fun part1(input: List<String>): Int {
        return input
            .makeIntervals()
            .count { (firstInterval, secondInterval) ->
                val intersection = firstInterval.intersect(secondInterval.toSet())
                intersection.size == firstInterval.size || intersection.size == secondInterval.size
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .makeIntervals()
            .count { it.first.intersect(it.second.toSet()).isNotEmpty() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

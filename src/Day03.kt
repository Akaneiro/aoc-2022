fun main() {

    fun Char.calculatePriority(): Int {
        var priority = this.lowercaseChar() - 'a' + 1
        if (this.isUpperCase()) priority += 26
        return priority
    }

    fun List<String>.asListOfCharLists() =
        this
            .asSequence()
            .map { it.toCharArray().toList() }

    fun part1(input: List<String>): Int {
        return input.asListOfCharLists()
            .map { it.chunked(it.size / 2) }
            .map { it.first() to it.last() }
            .map { (first, second) -> first.intersect(second.toSet()) }
            .map { it.sumOf { it.calculatePriority() } }
            .sum()
    }

    fun part2(input: List<String>): Int {
        return input.asListOfCharLists()
            .chunked(3)
            .map { group ->
                val first = group.first()
                val resultList = mutableListOf<Char>().apply { addAll(first) }
                group.forEach { s ->
                    val intersectWithCurrent = first.intersect(s.toSet())
                    val intersectWithResult = resultList.intersect(intersectWithCurrent)
                    resultList.clear()
                    resultList.addAll(intersectWithResult)
                }
                resultList
            }
            .map { it.first() }
            .map { it.calculatePriority() }
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

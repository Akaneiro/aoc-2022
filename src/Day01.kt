fun main() {

    fun getElvesCalSums(input: List<String>): List<Int> =
        mutableListOf<Int>().apply {
            var currentCalSum = 0
            input.forEachIndexed { index, element ->
                when {
                    element.isBlank() -> {
                        this.add(currentCalSum)
                        currentCalSum = 0
                    }

                    index == input.size - 1 -> {
                        currentCalSum += element.toInt()
                        this.add(currentCalSum)
                    }

                    else -> currentCalSum += element.toInt()
                }
            }
        }

    fun part1(input: List<String>): Int = getElvesCalSums(input).max()

    fun part2(input: List<String>): Int = getElvesCalSums(input).sorted().takeLast(3).sum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

package commands.dice

import kotlin.random.Random

class Dice(private val n: Int, private val size: DiceSize) {

    fun roll(): Array<Int> {
        return Array(n) { Random.nextInt(1, size.value + 1) }
    }

    companion object {
        private val regex = Regex("^([1-9]\\d*)d(\\d+)\$")

        fun fromString(input: String): Result<Dice> {
            val capture = regex.matchEntire(input)?.groupValues

            return if (capture == null) Result.failure(ParseError("Unable to parse, must be of the form <number>d<size>"))
            else {
                val n = capture[1].toInt()
                val size = capture[2].toInt()
                val diceSize = DiceSize.values().find { it.value == size }
                if (diceSize == null) Result.failure(SizeError("Dice size must be 4, 6, 8, 10, 12, 20, or 100"))
                else Result.success(Dice(n, diceSize))
            }
        }
    }
}
package commands.dice

import arrow.core.Either
import kotlin.random.Random

class Dice(private val n: Int, private val size: DiceSize) {

    fun roll(): Array<Int> = Array(n) { Random.nextInt(1, size.value + 1) }

    companion object {
        private val regex = Regex("^([1-9]\\d*)d(\\d+)\$")

        fun fromString(input: String): Either<Error, Dice> {
            val capture = regex.matchEntire(input)?.groupValues
                ?: return Either.Left(Error.Parse)

            val n = capture[1].toInt()
            val size = capture[2].toInt()
            val diceSize = DiceSize.values().find { it.value == size }
                ?: return Either.Left(Error.Size)

            return Either.Right(Dice(n, diceSize))
        }
    }
}
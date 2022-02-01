package commands.dice

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class DiceTest {

    @Test
    fun `test fromString 2d6`() {
        val result = Dice.fromString("2d6")
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()?.roll()?.size == 2)
    }

    @Test
    fun `test wrong fromString`() {
        val dice = Dice.fromString("abc")
        assertTrue(dice.isFailure)
        assertTrue(dice.exceptionOrNull() is ParseError)
    }

    @Test
    fun `test wrong fromString 2d7`() {
        val dice = Dice.fromString("2d7")
        assertTrue(dice.isFailure)
        assertTrue(dice.exceptionOrNull() is SizeError)
    }
}
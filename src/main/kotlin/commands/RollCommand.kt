package commands

import arrow.core.Either
import commands.dice.Dice
import commands.dice.Error
import dev.kord.core.event.message.MessageCreateEvent

object RollCommand : Command() {
    override val name = "roll"

    override suspend fun execute(event: MessageCreateEvent) {
        val input = event.message.content.split(" ")
        if (input.size > 1) {
            val channel = event.message.channel
            val message = when (val dice = Dice.fromString(input[1])) {
                is Either.Left -> when (dice.value) {
                    is Error.Parse -> "Unable to parse, must be of the form <number>d<size>"
                    is Error.Size -> "Dice size must be 4, 6, 8, 10, 12, 20, or 100!"
                }
                is Either.Right -> dice.value.roll().let { rolls ->
                    "${rolls.joinToString(" + ")} = ${rolls.sum()}"
                }
            }
            channel.createMessage(message)
        }
    }
}

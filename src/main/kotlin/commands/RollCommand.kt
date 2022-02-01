package commands

import commands.dice.Dice
import dev.kord.core.event.message.MessageCreateEvent

object RollCommand : Command() {
    override val name = "roll"

    override suspend fun execute(event: MessageCreateEvent) {
        val input = event.message.content.split(" ")
        if (input.size > 1) {
            val channel = event.message.channel
            Dice.fromString(input[1]).fold(
                onFailure = { channel.createMessage("Wrong input you fool! ${it.message}") },
                onSuccess = {
                    channel.createMessage(
                        it.roll().let { rolls ->
                            "${rolls.joinToString(" + ")} = ${rolls.sum()}"
                        })
                }
            )
        }
    }
}
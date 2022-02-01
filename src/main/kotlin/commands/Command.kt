package commands

import dev.kord.core.event.message.MessageCreateEvent

abstract class Command {
    val prefix = "#"
    abstract val name: String
    abstract suspend fun execute(event: MessageCreateEvent)
}
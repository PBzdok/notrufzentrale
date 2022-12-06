package commands.initiative

import commands.Command
import dev.kord.core.event.message.MessageCreateEvent

object AddInitiativeCommand : Command() {
    override val name = "add_initiative"

    override suspend fun execute(event: MessageCreateEvent) {
        val user = event.message.content.substringAfter(" ") //#add_initiative schmai
        State.initiative.addUserInitiative(user)
        event.message.channel.createMessage(State.initiative.toString())
    }
}
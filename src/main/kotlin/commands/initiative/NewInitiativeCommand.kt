package commands.initiative

import commands.Command
import commands.initiative.State.initiative
import dev.kord.core.event.message.MessageCreateEvent

object NewInitiativeCommand : Command() {
    override val name = "new_initiative"

    override suspend fun execute(event: MessageCreateEvent) {
        val users = event.message.content //#new_initiative schmai schmils smavy schmitzitz
            .substringAfter(" ")
            .split(" ")
        val dq = ArrayDeque(users)

        initiative = (1..10).toList()
            .shuffled()
            .associateWith { dq.removeFirstOrNull() ?: PLACEHOLDER }
            .toMutableMap()

        val message = State.initiativeToString()
        event.message.channel.createMessage(message)
    }
}
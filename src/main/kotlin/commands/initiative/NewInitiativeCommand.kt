package commands.initiative

import commands.Command
import dev.kord.core.event.message.MessageCreateEvent

object NewInitiativeCommand : Command() {
    override val name = "new_initiative"

    override suspend fun execute(event: MessageCreateEvent) {
        val users = event.message.content //#new_initiative schmai schmils smavy schmitzitz
            .substringAfter(" ")
            .split(" ")
        val dq = ArrayDeque(users)

        State.initiative = Initiative(
            (1..10).toList()
                .shuffled()
                .associateWith { dq.removeFirstOrNull() ?: PLACEHOLDER }
                .toMutableMap()
        )

        event.message.channel.createMessage(State.initiative.toString())
    }
}
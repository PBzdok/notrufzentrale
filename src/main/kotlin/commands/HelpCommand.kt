package commands

import dev.kord.common.Color
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.event.message.MessageCreateEvent

object HelpCommand : Command() {
    override val name = "help"

    override suspend fun execute(event: MessageCreateEvent) {
        event.message.channel.createEmbed {
            color = Color(239, 128, 128)
            title = "Zentrale hier, wie kann ich helfen?"
            field {
                name = "`${prefix}mm <deine Frage>`"
                value = "Frag die magische Miesmuschel"
                inline = false
            }
            field {
                name = "`${prefix}roll <Würfel>`"
                value = "Würfel nach Standard Notation, z.B. `#roll 2d10`"
                inline = false
            }
            footer {
                text = "Find me @ https://github.com/pbzdok/notrufzentrale"
            }
        }
    }
}
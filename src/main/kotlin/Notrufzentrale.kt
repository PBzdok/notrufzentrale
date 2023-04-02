import commands.Command
import commands.HelpCommand
import commands.MusselCommand
import commands.RollCommand
import commands.initiative.AddInitiativeCommand
import commands.initiative.NewInitiativeCommand
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

suspend fun main(args: Array<String>) {
    if (args.size != 1) {
        throw IllegalArgumentException("Please provide a API Token!")
    }

    val kord = Kord(args[0])
    val commands = buildCommands()

    kord.on<MessageCreateEvent> {
        if (message.author?.isBot != false) return@on
        val commandInput = message.content.split(" ")[0]
        commands.find { (it.prefix + it.name) == commandInput }
                ?.execute(this)
    }

    logger.info { "Notrufzentrale checking in..." }

    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}

fun buildCommands(): List<Command> =
        listOf(
                HelpCommand,
                MusselCommand,
                RollCommand,
                NewInitiativeCommand,
                AddInitiativeCommand
        )

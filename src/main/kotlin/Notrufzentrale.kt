import commands.Command
import commands.HelpCommand
import commands.MusselCommand
import commands.RollCommand
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on

suspend fun main(args: Array<String>) {
    if (args.size != 1) {
        throw IllegalArgumentException("Please provide a API Token!")
    }

    val client = Kord(args[0])
    val commands = buildCommands()

    client.on<MessageCreateEvent> {
        if (message.author?.isBot != false) return@on

        val commandInput = message.content.split(" ")[0]

        commands
            .find { (it.prefix + it.name) == commandInput }
            ?.execute(this)
    }

    client.login()
}

fun buildCommands(): List<Command> {
    return listOf(
        HelpCommand,
        MusselCommand,
        RollCommand
    )
}
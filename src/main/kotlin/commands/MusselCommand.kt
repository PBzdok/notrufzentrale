package commands

import dev.kord.core.event.message.MessageCreateEvent

object MusselCommand : Command() {
    override val name = "mm"

    private val answers = arrayListOf(
        "As I see it, yes",
        "Yes",
        "No",
        "Very likely",
        "Not even close",
        "Maybe",
        "Very unlikely",
        "Janni's mom told me yes",
        "Janni's mom told me no",
        "Ask again later",
        "Better not tell you now",
        "Concentrate and ask again",
        "Don't count on it",
        " It is certain",
        "My sources say no",
        "Outlook good",
        "You may rely on it",
        "Very Doubtful",
        "Without a doubt"
    )

    override suspend fun execute(event: MessageCreateEvent) {
        event.message.channel.createMessage(answers.random())
    }

}
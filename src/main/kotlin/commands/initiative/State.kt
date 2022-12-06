package commands.initiative


const val PLACEHOLDER = "🅱️"

object State {
    var initiative = mutableMapOf<Int, String>()

    fun addUserInitiative(username: String) {
        val remainingEntries = initiative.filter { it.value == PLACEHOLDER }
        val userInit = remainingEntries.keys.random()
        initiative[userInit] = username
    }

    fun initiativeToString(): String = initiative.toSortedMap() .map { "Initative `${it.key}` für `${it.value}`" }.joinToString("\n")
}
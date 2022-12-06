package commands.initiative


const val PLACEHOLDER = "✖️"

object State {
    var initiative = Initiative()
}

data class Initiative(
    private val entries: MutableMap<Int, String> = mutableMapOf()
) {
    fun addUserInitiative(username: String) {
        val userInit = entries
            .filter { it.value == PLACEHOLDER }
            .keys.random()
        entries[userInit] = username
    }

    override fun toString(): String {
        return entries
            .toSortedMap()
            .map { "Initative `${it.key}` für `${it.value}`" }
            .joinToString("\n")
    }
}
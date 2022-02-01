package commands.dice

sealed class DiceError(message: String) : Exception(message)
class ParseError(message: String) : DiceError(message)
class SizeError(message: String) : DiceError(message)
package commands.dice

sealed class Error {
    object Parse : Error()
    object Size : Error()
}
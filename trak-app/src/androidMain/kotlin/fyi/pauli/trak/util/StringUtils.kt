package fyi.pauli.trak.util

const val MAIL_PATTERN = "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})"
private val matcher = Regex(MAIL_PATTERN)

object StringUtils {

    fun isMail(string: String): Boolean {
        return matcher.matches(string)
    }
}
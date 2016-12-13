package org.nexivo.kt.specifics.exceptions

private val EXCEPTION_MESSAGE_SPLITTER: Regex = Regex("\\.|\\$")

infix fun Throwable?.`parse message`(parser:(String, String) -> String?): String? {

    if (this == null) {
        return null
    }

    val sections: List<String> = this.toString().split(":")

    return parser(sections.first().split(EXCEPTION_MESSAGE_SPLITTER).last(), sections.last().trim())
}

package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?> {
        val parts : List<String>? = fullName?.trim()?.split(" ")

        val firstName_ = parts?.getOrNull(0)

        val firstName = if (firstName_.isNullOrEmpty()) "null" else parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1) ?: "null"

        return Pair(firstName, lastName)
    }
    fun transliteration(payload: String, divider: String = " " ): String? {
        TODO("may be later")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        TODO("may be later")
    }

}
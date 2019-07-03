package ru.skillbranch.devintensive.utils

import java.lang.Character.toUpperCase

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?> {
        val parts : List<String>? = fullName?.trim()?.split(" ")
        val firstName: String? = parts?.getOrNull(0)
        val lastName: String? = parts?.getOrNull(1)

        if (firstName.isNullOrEmpty()) {
            return Pair(null, null) // lastName can't be set when ever firstName is not declared
        }
        else {
            if (lastName.isNullOrEmpty())
                return Pair(firstName, null)
            else
                return Pair(firstName, lastName)
        }
        return Pair(firstName, lastName)
    }
    fun transliteration(payload: String, divider: String = " " ): String? {
        TODO("may be later")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var initials: String? = "";

        if (firstName?.trim().isNullOrEmpty()) {
            return null // lastName can't be set when ever firstName is not declared
        }
        initials = firstName?.trim()?.toUpperCase()?.substring(0, 1)
        if (!lastName?.trim().isNullOrEmpty()) {
            initials += lastName?.trim()?.toUpperCase()?.substring(0, 1)
        }
        return initials
    }

}
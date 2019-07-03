package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.split(" ")
        val firstName: String? = parts?.getOrNull(0)
        val lastName: String? = parts?.getOrNull(1)

        if (firstName.isNullOrEmpty()) {
            return Pair(null, null) // lastName can't be set when ever firstName is not declared
        } else {
            if (lastName.isNullOrEmpty())
                return Pair(firstName, null)
            else
                return Pair(firstName, lastName)
        }
        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String? {
        if (payload.trim().isEmpty())
            return payload

        var engOutput: String? = ""

        //val map= hashMapOf<String, String>()
        val map = mapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )


        engOutput = buildString {
            payload.replace(" ", divider)
                .toCharArray()
                .forEach {
                    val symbol = map.get(it.toLowerCase().toString())
                    append(
                        if (symbol == null)
                            it.toString()
                        else if (it.isUpperCase())
                            symbol.capitalize()
                        else symbol
                    )
                }
        }
        return engOutput
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
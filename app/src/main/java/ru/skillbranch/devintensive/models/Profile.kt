package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils

data class Profile(
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {
    val nickName: String by lazy {
        Utils.transliteration("$firstName $lastName", "_") ?: "John Doe"
    }
    val rank: String = "Junior Android Developer"

    fun toMap() = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )

    companion object {
        fun validateRepository(repositoryString: String): Boolean {

            val normalizeUrl = repositoryString.toLowerCase().trim()
            if (normalizeUrl.isEmpty())
                return true

            if (!normalizeUrl.matches("(https://)?(www.)?github.com/[a-z0-9]+(-[a-z0-9]+)?(/)?".toRegex()))
                return false
            val githubNickname = normalizeUrl.substring(repositoryString.indexOf("github.com") + 11).dropLastWhile { it == '/' }
            val excludes = setOf(
                "enterprise",
                "features",
                "topics",
                "collections",
                "trending",
                "events",
                "marketplace",
                "pricing",
                "nonprofit",
                "customer-stories",
                "security",
                "login",
                "join"
            )
            return !excludes.contains(githubNickname)

        }

    }
}
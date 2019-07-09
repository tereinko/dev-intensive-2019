package ru.skillbranch.devintensive.extensions

fun String.truncate(len: Int = 16): String {
    var s = this.trimStart().trimEnd()
    if (s.length <= len)
        return s
    s = s.substring(0, len)
    return s.trimEnd() + "..."
}

fun String.stripHtml(): String {
    return ""
}
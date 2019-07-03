package ru.skillbranch.devintensive.models

import java.util.*

class TextMessages (
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var text: String?

): BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String {
        return ""
    }
}
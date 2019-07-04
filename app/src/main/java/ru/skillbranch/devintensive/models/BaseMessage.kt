package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage (
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()

) {
    abstract fun formatMessage() : String
    companion object AbstractFactory {
        var lastId = -1
        fun makeMessage(from: User?, chat: Chat, date: Date, payload: Any?,type: String = "text",
                        isIncoming: Boolean = false): BaseMessage {
            lastId++
            return when (type) {
                "text" -> TextMessage("$lastId",from, chat, date = date, text = payload as String, isIncoming = isIncoming)
                else -> ImageMessage("$lastId",from, chat, date = date, image = payload as String, isIncoming = isIncoming)
            }
        }
    }
}
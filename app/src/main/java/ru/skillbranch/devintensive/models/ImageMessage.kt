//package ru.skillbranch.devintensive.models
//
//import ru.skillbranch.devintensive.extensions.humanizeDiff
//import java.util.*
//
//class ImageMessage (
//        id: String,
//        from: User?,
//        chat: Chat,
//        isIncoming: Boolean = false,
//        date: Date = Date(),
//        var image: String?
//
//) : BaseMessage(id, from, chat, isIncoming, date) {
//    override fun formatMessage(): String {
//        return ("id:$id ${from?.firstName.toString()} ${if (isIncoming) "получил" else "отправил"} изображение \"$image\" ${date.humanizeDiff()}")
//    }
//}
package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.models.data.Chat
import ru.skillbranch.devintensive.models.data.User
import java.util.*

class ImageMessage (
    id:String,
    from: User,
    chat: Chat,
    isIncoming : Boolean = false,
    date: Date = Date(),
    isReaded:Boolean = false,
    var image:String
) : BaseMessage(id, from, chat, isIncoming, date,isReaded)
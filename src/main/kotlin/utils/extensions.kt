package com.magway.uwu.utils

import com.github.kotlintelegrambot.entities.MessageEntity

fun String.toMarkdown(entities: List<MessageEntity>?): String {
    val markdownContent = StringBuilder(this)
    var globalOffset = 0
    entities?.sortedBy { it.offset }?.forEach { entity ->
        when (entity.type) {
            MessageEntity.Type.BOLD -> {
                markdownContent.insert(entity.offset + globalOffset, "**")
                markdownContent.insert(entity.offset + globalOffset + entity.length + 2, "**")
                globalOffset += 4
            }

            MessageEntity.Type.ITALIC -> {
                markdownContent.insert(entity.offset + globalOffset, "*")
                markdownContent.insert(entity.offset + globalOffset + entity.length + 1, "*")
                globalOffset += 2
            }

            MessageEntity.Type.TEXT_LINK -> {
                markdownContent.insert(entity.offset + globalOffset, "[")
                markdownContent.insert(entity.offset + globalOffset + entity.length + 1, "](${entity.url})")
                globalOffset += 4 + (entity.url?.length ?: 0)
            }

            else -> {}
        }
        println(markdownContent.toString())
    }

    return markdownContent.toString()
}

//MENTION,
//HASHTAG,
//CASHTAG,
//BOT_COMMAND,
//URL,
//EMAIL,
//PHONE_NUMBER,
//BOLD,
//ITALIC,
//UNDERLINE,
//STRIKETHROUGH,
//CODE,
//PRE,
//TEXT_LINK,
//TEXT_MENTION,
//SPOILER;
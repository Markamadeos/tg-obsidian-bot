package com.magway.uwu.utils

import com.github.kotlintelegrambot.entities.MessageEntity

fun String.toMarkdown(entities: List<MessageEntity>?): String {
    val markdownContent = StringBuilder(this)
    entities?.forEach { entity ->
        when (entity.type) {
            MessageEntity.Type.BOLD -> {
                markdownContent.insert(entity.offset, "**").insert(entity.offset + entity.length + 2, "**")
            }

            MessageEntity.Type.ITALIC -> {
                markdownContent.insert(entity.offset, "*").insert(entity.offset + entity.length + 1, "*")
            }

            else -> {}
        }
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
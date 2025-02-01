package com.magway.uwu

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.github.kotlintelegrambot.entities.reaction.ReactionType
import config.Config
import kotlinx.serialization.json.Json
import java.io.File

fun main(args: Array<String>) {
    println("о привет")
    val json = Json { ignoreUnknownKeys = true }
    val config: Config = json.decodeFromString(Config.serializer(), File(args.first()).readText())

    val botToken = config.token
    val username = config.username

    val bot = bot {
        token = botToken
        dispatch {
            message {
                val message = update.message
                println("receive message from ${message?.chat?.username} $message")
                if (message != null && message.chat.username == username) {
                    this.bot.setMessageReaction(
                        chatId = ChatId.fromId(message.chat.id),
                        messageId = message.messageId,
                        reaction = listOf(ReactionType.Emoji("❤\uFE0F\u200D\uD83D\uDD25")),
                        isBig = true
                    )
                    saveToMarkdown(message)
                }
            }
        }
    }

    bot.startPolling()
}

fun saveToMarkdown(message: Message) {
    val markdownFile = File("/data/forwarded_messages.md")
    if (!markdownFile.exists()) {
        markdownFile.createNewFile()
    }

    val markdownContent = StringBuilder()

    message.forwardFrom?.let {
        markdownContent.append("**Forwarded from [${it.username}](https://t.me/${it.username}/${message.chat.id})**")
    }

    message.forwardFromChat?.let {
        markdownContent.append("**Forwarded from [${it.title}](https://t.me/${it.username}/${message.chat.id})**")
    }
    println(markdownContent.toString())

    markdownContent.append("**Текст:** ${message.text}\n\n")


    markdownFile.appendText(markdownContent.toString())
}
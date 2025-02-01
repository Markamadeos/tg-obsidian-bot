package com.magway.uwu

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.github.kotlintelegrambot.entities.reaction.ReactionType
import com.magway.uwu.utils.toMarkdown
import config.Config
import kotlinx.serialization.json.Json
import java.io.File

fun main(args: Array<String>) {
    println("O hi! Checking config.json file...")
    val configFile = try {
        File(args.first())
    } catch (e: Exception) {
        println("Oh nooo! looks like we have some problem with config.json file...")
        throw e
    }

    val config: Config = parseConfigFile(configFile)
    val bot = setupBot(config)
    println("Bot username is ${bot.getMe().get().username}")
    bot.startPolling()
    println("Start polling...")
}

fun saveToMarkdown(message: Message) {
    val markdownFile = File("/data/inbox.md")
    if (!markdownFile.exists()) {
        markdownFile.createNewFile()
    }

    val markdownContent = StringBuilder()

    message.forwardFrom?.let {
        markdownContent.append("**Forwarded from [${it.username}](https://t.me/${it.username}/${message.chat.id})**")
    }

    message.forwardSenderName?.let {
        markdownContent.append("**Forwarded from $it**")
    }

    message.forwardFromChat?.let {
        markdownContent.append("**Forwarded from [${it.title}](https://t.me/${it.username}/${message.chat.id})**")
    }

    markdownContent.append("\n\n")

    markdownContent.append("${message.text?.toMarkdown(message.entities)}\n\n")
    markdownContent.append("${message.caption?.toMarkdown(message.captionEntities)}\n\n")

    markdownFile.appendText(markdownContent.toString())
}

fun parseConfigFile(file: File): Config {
    val json = Json { ignoreUnknownKeys = true }
    val config = json.decodeFromString<Config>(file.readText())
    if (config.token.isBlank()) {
        throw Exception("Empty token detected! Please check your config file")
    } else {
        println("Okay, token last symbols is ...${config.token.takeLast(3)}")
    }

    if (config.token.isBlank()) {
        throw Exception("Empty username detected! Please check your config file")
    } else {
        println("Okay, your username is ${config.username}")
    }
    return config
}

fun setupBot(config: Config): Bot {
    val bot = bot {
        token = config.token
        dispatch {
            message {
                val message = update.message
                println("receive message from ${message?.chat?.username} $message")
                if (message != null && message.chat.username == config.username) {
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
    return bot
}
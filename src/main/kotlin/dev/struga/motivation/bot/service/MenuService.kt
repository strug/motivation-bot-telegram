package dev.struga.motivation.bot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Service
class MenuService {

    fun createTypeMotivationMenu(chatId: Long): SendMessage {
        return SendMessage().apply {
            setChatId(chatId)
            text = "Choose type of content:"
            replyMarkup = InlineKeyboardMarkup(
                listOf(
                    createMenuItem("music", "music"),
                    createMenuItem("video", "video"),
                    createMenuItem("text", "text"),
                    createMenuItem("random", "random")
                ),
            )
        }
    }

    fun createForMotivationMenu(chatId: Long): SendMessage {
        return SendMessage().apply {
            setChatId(chatId)
            text = "Choose type of activity:"
            replyMarkup = InlineKeyboardMarkup(
                listOf(
                    createMenuItem("work", "work"),
                    createMenuItem("learn", "learn"),
                    createMenuItem("read", "read"),
                    createMenuItem("sport", "sport"),
                    createMenuItem("meditation", "meditation"),
                ),
            )
        }
    }

    private fun createMenuItem(textValue: String, callbackDataValue: String) = listOf(
        InlineKeyboardButton().apply {
            text = textValue
            callbackData = callbackDataValue
        },
    )
}
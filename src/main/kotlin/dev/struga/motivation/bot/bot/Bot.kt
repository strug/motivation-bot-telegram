package dev.struga.motivation.bot.bot

import dev.struga.motivation.bot.service.MenuService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

private val log = KotlinLogging.logger {}

@Component
class Bot(private val menuService: MenuService) : TelegramLongPollingBot() {

    @Value("\${bot.username}")
    private val botUsername: String? = null

    @Value("\${bot.token}")
    private val botToken: String? = null

    override fun onUpdateReceived(update: Update) {
        try {
            if (update.hasMessage()) {
                if (update.message.text.equals("/start")) {
                    handleStartMessage(update)
                }
            } else if (update.hasCallbackQuery()) {
                if (update.callbackQuery.data.isNotEmpty()) {
                    handleCallback(update)
                }
            }
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    private fun handleStartMessage(update: Update) {
        try {
            execute(menuService.createTypeMotivationMenu(update.message.chatId))
        } catch (e: TelegramApiException) {
            log.error { e }
        }
    }

    private fun handleCallback(update: Update) {
        try {
            execute(menuService.createForMotivationMenu(update.callbackQuery.from.id))
        } catch (e: TelegramApiException) {
            log.error { e }
        }
    }

    override fun getBotUsername(): String {
        return botUsername!!
    }

    override fun getBotToken(): String {
        return botToken!!
    }
}
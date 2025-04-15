package com.nghlong3004.telegrambot.server.utils;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import com.nghlong3004.telegrambot.server.controller.DatabaseController;

public class TelegramBotUtil implements LongPollingSingleThreadUpdateConsumer {
  private static final Logger LOGGER = Logger.getLogger(TelegramBotUtil.class);
  private final TelegramClient TELEGRAM_CLIENT;

  protected TelegramBotUtil(String token) {
    TELEGRAM_CLIENT = new OkHttpTelegramClient(token);
  }

  @Override
  public void consume(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      processIncomingMessage(update.getMessage());
    }
  }

  private void processIncomingMessage(Message message) {
    String userMessage = message.getText();
    long chatId = message.getChatId();
    long telegramId = message.getChat().getId();
    String firstName = message.getChat().getFirstName();
    String lastName = message.getChat().getLastName();
    String fullName =
        (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");

    LOGGER
        .info("[USER] Message received from " + fullName + " (" + telegramId + "): " + userMessage);

    DatabaseController.getDatabaseService().insertUserIfNotExists(fullName.trim(), telegramId);

    String botReply;
    if (userMessage.trim().equalsIgnoreCase("/btc")) {
      botReply = DatabaseController.getDatabaseService().getTodayServerHistoriesByKeyword("BTC");
    } else if (userMessage.trim().equalsIgnoreCase("/eth")) {
      botReply = DatabaseController.getDatabaseService().getTodayServerHistoriesByKeyword("ETH");
    } else if (userMessage.split(" ")[0].equalsIgnoreCase("/askai")) {
      botReply =
          ObjectUtilContainer.getApiOpenAiUtil().ask(userMessage.replaceFirst("^/askai\\s*", ""));
    } else {
      botReply = ("gõ /eth hoặc /btc để xem thị trường hoặc gõ /askai để bắt đầu chat với ai");
    }

    DatabaseController.getDatabaseService().insertUserMessage(telegramId, userMessage, botReply);

    SendMessage response = SendMessage.builder().chatId(chatId).text(botReply).build();

    try {
      TELEGRAM_CLIENT.execute(response);
      LOGGER.info("[BOT] Sent reply to user :" + telegramId);
    } catch (TelegramApiException e) {
      LOGGER.error("[BOT] Error sending message" + e);
    }

  }

}

package com.nghlong3004.telegrambot.server.utils;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import com.nghlong3004.telegrambot.server.service.APIService;
import com.nghlong3004.telegrambot.server.service.DatabaseService;

public class TelegramBotUtil implements LongPollingSingleThreadUpdateConsumer {
  private static final Logger LOGGER = Logger.getLogger(TelegramBotUtil.class);
  private final TelegramClient TELEGRAM_CLIENT;
  private final APIService API_SERVICE;
  private final DatabaseService DATABASE_SERVICE;

  protected TelegramBotUtil(String token) {
    TELEGRAM_CLIENT = new OkHttpTelegramClient(token);
    API_SERVICE = new APIService();
    DATABASE_SERVICE = new DatabaseService();
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

    LOGGER.info("Receive messages from " + fullName + " (" + telegramId + "): " + userMessage);

    DATABASE_SERVICE.insertUserIfNotExists(fullName.trim(), telegramId);

    String botReply = API_SERVICE.askOpenAi(userMessage);

    DATABASE_SERVICE.insertUserMessage(telegramId, userMessage, botReply);

    SendMessage response = SendMessage.builder().chatId(chatId).text(botReply).build();

    try {
      TELEGRAM_CLIENT.execute(response);
      LOGGER.info("Feedback sent to :" + telegramId);
    } catch (TelegramApiException e) {
      LOGGER.error(e);
    }

  }

}

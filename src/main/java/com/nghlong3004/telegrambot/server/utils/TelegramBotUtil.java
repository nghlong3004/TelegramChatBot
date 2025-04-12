package com.nghlong3004.telegrambot.server.utils;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class TelegramBotUtil implements LongPollingSingleThreadUpdateConsumer {
  
  private final TelegramClient telegramClient;

  public TelegramBotUtil(String token) {
    telegramClient = new OkHttpTelegramClient(token);
  }

  @Override
  public void consume(Update update) {
    if (update.hasMessage()) {
      Message message = update.getMessage();
      String text = message.getText();
      long chatId = message.getChatId();
      long userId = message.getChat().getId();
      String firstnameUser = message.getChat().getFirstName();
      String lastnameUser = message.getChat().getLastName();

      SendMessage response = SendMessage
                            .builder()
                            .text(text)
                            .chatId(chatId)
                            .build();
      log(firstnameUser, lastnameUser, String.valueOf(userId), text, text);
      
      try {
        telegramClient.execute(response);
      } catch (TelegramApiException e) {
        LOGGER.debug(e.getMessage());
      }
      
    }

  }

  private void log(String firstName, String lastName, String userId, String txt,
      String botAnswer) {
    LOGGER.info("Message from " + firstName + " " + lastName + ". (id = " + userId
        + ") \n Text - " + txt);
    LOGGER.info("Bot answer: \n Text - " + botAnswer);
  }
  
  private static final Logger LOGGER = Logger.getLogger(TelegramBotUtil.class);
  static {
    try {
      PatternLayout layout = new PatternLayout();
      layout.setConversionPattern("[%-5l] %d{yyyy-MM-dd HH:mm:ss.SSS} %c{1}:%L - %m%n");
      ConsoleAppender appender = new ConsoleAppender(layout);
      appender.setName("STDOUT");
      LOGGER.addAppender(appender);
      LOGGER.setLevel(Level.DEBUG);
      LOGGER.info("TelegramBotUtil::Log4j Setup ready");

    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.fatal("TelegramBotUtil::Problem while setting up Log4j");
    }
  }

}

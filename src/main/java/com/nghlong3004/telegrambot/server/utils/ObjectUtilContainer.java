package com.nghlong3004.telegrambot.server.utils;

public class ObjectUtilContainer {
  private static final PropertyUtil PROPERTY_UTIL = new PropertyUtil();

  private static final APIOpenAIUtil OPENAI = new APIOpenAIUtil(PROPERTY_UTIL.getOpenAIApiKey());

  private static final TelegramBotUtil TELEGRAM_BOT =
      new TelegramBotUtil(PROPERTY_UTIL.getTelegramToken());

  public static PropertyUtil getPropertyUtil() {
    return PROPERTY_UTIL;
  }
  
  public static APIOpenAIUtil getOpenAi() {
    return OPENAI;
  }

  public static TelegramBotUtil getTelegramBot() {
    return TELEGRAM_BOT;
  }

}

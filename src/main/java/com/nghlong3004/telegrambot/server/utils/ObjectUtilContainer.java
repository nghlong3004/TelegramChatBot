package com.nghlong3004.telegrambot.server.utils;

public class ObjectUtilContainer {
  private static final PropertyUtil PROPERTY_UTIL = new PropertyUtil();

  private static final APIOpenAIUtil OPENAI = new APIOpenAIUtil(PROPERTY_UTIL.getOpenAIApiKey());

  private static final TelegramBotUtil BOT_UTIL =
      new TelegramBotUtil(PROPERTY_UTIL.getTelegramToken());

  public static APIOpenAIUtil getOpenAi() {
    return OPENAI;
  }

  public static TelegramBotUtil getBotUtil() {
    return BOT_UTIL;
  }

}

package com.nghlong3004.telegrambot.server.utils;

import com.nghlong3004.telegrambot.server.configuration.DatabaseConfiguration;

public class ObjectUtilContainer {
  private static final PropertyUtil PROPERTY_UTIL = new PropertyUtil();

  private static final DatabaseConfiguration DATABASE_CONFIGURATION = new DatabaseConfiguration(
      PROPERTY_UTIL.getDbUrl(), PROPERTY_UTIL.getDbUsername(), PROPERTY_UTIL.getDbPassword());

  private static final DatabaseUtil DATABASE_UTIL = new DatabaseUtil(DATABASE_CONFIGURATION);

  private static final APIOpenAIUtil OPENAI = new APIOpenAIUtil(PROPERTY_UTIL.getOpenAIApiKey());

  private static final TelegramBotUtil TELEGRAM_BOT =
      new TelegramBotUtil(PROPERTY_UTIL.getTelegramToken());

  private ObjectUtilContainer() {

  }

  public static PropertyUtil getPropertyUtil() {
    return PROPERTY_UTIL;
  }

  public static APIOpenAIUtil getOpenAi() {
    return OPENAI;
  }

  public static TelegramBotUtil getTelegramBot() {
    return TELEGRAM_BOT;
  }

  public static DatabaseUtil getDatabaseUtil() {
    return DATABASE_UTIL;
  }

}

package com.nghlong3004.telegrambot.server.utils;

import com.nghlong3004.telegrambot.server.configuration.DatabaseConfiguration;

public class ObjectUtilContainer {
  private static final PropertyUtil PROPERTY_UTIL = new PropertyUtil();

  private static final DatabaseConfiguration DATABASE_CONFIGURATION =
      new DatabaseConfiguration(PROPERTY_UTIL.getDbUrl(), PROPERTY_UTIL.getDbUsername(),
          PROPERTY_UTIL.getDbPassword(), PROPERTY_UTIL.getDbClassName());

  private static final DatabaseUtil DATABASE_UTIL = new DatabaseUtil(DATABASE_CONFIGURATION);

  private static final APIBinanceUtil API_BINANCE_UTIL = new APIBinanceUtil();

  private static final APIOpenAIUtil API_OPEN_AI_UTIL =
      new APIOpenAIUtil(PROPERTY_UTIL.getOpenAIApiKey());

  private static final TelegramBotUtil TELEGRAM_BOT_UTIL =
      new TelegramBotUtil(PROPERTY_UTIL.getTelegramToken());

  private ObjectUtilContainer() {

  }

  public static PropertyUtil getPropertyUtil() {
    return PROPERTY_UTIL;
  }

  public static APIOpenAIUtil getApiOpenAiUtil() {
    return API_OPEN_AI_UTIL;
  }

  public static TelegramBotUtil getTelegramBotUtil() {
    return TELEGRAM_BOT_UTIL;
  }

  public static DatabaseUtil getDatabaseUtil() {
    return DATABASE_UTIL;
  }

  public static APIBinanceUtil getApiBinanceUtil() {
    return API_BINANCE_UTIL;
  }

}

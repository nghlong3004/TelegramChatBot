package com.nghlong3004.telegrambot.server.utils;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertyUtil {
  private static final Logger LOGGER = Logger.getLogger(PropertyUtil.class);
  private final Properties properties = new Properties();;

  protected PropertyUtil() {
    try (InputStream input =
        getClass().getResourceAsStream("/com/nghlong3004/telegrambot/config/config.properties")) {
      if (input == null) {
        LOGGER.error("[Config] config.properties file not found in classpath.");
        return;
      }
      properties.load(input);
      LOGGER.info("[Config] Loaded config.properties successfully.");
    } catch (Exception e) {
      LOGGER.error("[Config] Error loading config.properties: ", e);
    }
  }

  public Properties getProperties() {
    return properties;
  }

  // --- OpenAI ---
  public String getOpenAIApiKey() {
    return getPropertyValue("openai.api_key");
  }

  // --- GitHub ---
  public String getGithubApiKey() {
    return getPropertyValue("github.api_key");
  }

  // --- Binance ---
  public String getBinanceApiKey() {
    return getPropertyValue("binance.api_key");
  }

  // --- Telegram ---
  public String getTelegramToken() {
    return getPropertyValue("tele.token");
  }

  public String getTelegramUsername() {
    return getPropertyValue("tele.username");
  }

  // --- Database ---
  public String getDbUrl() {
    return getPropertyValue("db.url");
  }

  public String getDbUsername() {
    return getPropertyValue("db.username");
  }

  public String getDbPassword() {
    return getPropertyValue("db.password");
  }

  public String getDbClassName() {
    return getPropertyValue("db.classname");
  }

  private String getPropertyValue(String key) {
    String value = properties.getProperty(key);
    if (value == null) {
      LOGGER.warn("[Config] Missing property: " + key);
    } else {
      LOGGER.info("[Config] Loaded property: " + key);
    }
    return value;
  }
}

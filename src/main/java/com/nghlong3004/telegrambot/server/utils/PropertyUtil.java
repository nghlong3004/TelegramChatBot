package com.nghlong3004.telegrambot.server.utils;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class PropertyUtil {

  private Properties properties;

  public PropertyUtil() {
    properties = new Properties();
    try (InputStream input =
        getClass().getResourceAsStream("/com/nghlong3004/telegrambot/config/config.properties")) {
      properties.load(input);
      LOGGER.info("Loading propertyUtil");
    } catch (Exception e) {
      LOGGER.debug(e.getMessage());
    }
  }

  public Properties getProperties() {
    return properties;
  }

  // open ai
  public String getOpenAIApiKey() {
    return getPropertyValue("openai.api_key");
  }

  // github 
  public String getGithubApiKey() {
    return getPropertyValue("github.api_key");
  }

  // binance
  public String getBinanceApiKey() {
    return getPropertyValue("binance.api_key");
  }

  // telegram
  public String getTelegramToken() {
    return getPropertyValue("tele.token");
  }

  public String getTelegramUsername() {
    return getPropertyValue("tele.username");
  }

  // database
  public String getDbUrl() {
    return getPropertyValue("db.url");
  }

  public String getDbUsername() {
    return getPropertyValue("db.username");
  }

  public String getDbPassword() {
    return getPropertyValue("db.password");
  }

  private String getPropertyValue(String property) {
    LOGGER.info("Call: " + property);
    return properties.getProperty(property);
  }

  private static final Logger LOGGER = Logger.getLogger(PropertyUtil.class);
  static {
    try {
      PatternLayout layout = new PatternLayout();
      layout.setConversionPattern("[%-5l] %d{yyyy-MM-dd HH:mm:ss.SSS} %c{1}:%L - %m%n");
      ConsoleAppender appender = new ConsoleAppender(layout);
      appender.setName("STDOUT");
      LOGGER.addAppender(appender);
      LOGGER.setLevel(Level.DEBUG);
      LOGGER.info("PropertyUtil::Log4j Setup ready");
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.fatal("PropertyUtil::Problem while setting up Log4j");
    }
  }

}

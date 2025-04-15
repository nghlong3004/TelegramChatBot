package com.nghlong3004.telegrambot.server.configuration;

public class DatabaseConfiguration {
  private final String DB_URL;
  private final String DB_USERNAME;
  private final String DB_PASSWORD;
  private final String DB_CLASS_NAME;

  public DatabaseConfiguration(String dbUrl, String dbUsername, String dbPassword, String className) {
    this.DB_URL = dbUrl;
    this.DB_USERNAME = dbUsername;
    this.DB_PASSWORD = dbPassword;
    this.DB_CLASS_NAME = className;
  }

  public String getDbUrl() {
    return DB_URL;
  }

  public String getDbUsername() {
    return DB_USERNAME;
  }

  public String getDbPassword() {
    return DB_PASSWORD;
  }

  public String getDbClassName() {
    return DB_CLASS_NAME;
  }
}

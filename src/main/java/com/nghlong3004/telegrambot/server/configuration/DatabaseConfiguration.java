package com.nghlong3004.telegrambot.server.configuration;

public class DatabaseConfiguration {
  private final String DB_URL;
  private final String DB_USERNAME;
  private final String DB_PASSWORD;

  public DatabaseConfiguration(String dbUrl, String dbUsername, String dbPassword) {
    this.DB_URL = dbUrl;
    this.DB_USERNAME = dbUsername;
    this.DB_PASSWORD = dbPassword;
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
}

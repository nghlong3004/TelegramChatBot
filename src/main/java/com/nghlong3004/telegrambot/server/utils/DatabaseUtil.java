package com.nghlong3004.telegrambot.server.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.nghlong3004.telegrambot.server.configuration.DatabaseConfiguration;

public class DatabaseUtil {
  private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class);

  private final DatabaseConfiguration DATABASE_CONFIGURATION;

  protected DatabaseUtil(DatabaseConfiguration databaseConfiguration) {
    DATABASE_CONFIGURATION = databaseConfiguration;
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      LOGGER.debug(e.getMessage());
    }
  }

  public Connection getConnection() throws SQLException {
    LOGGER.info("Connection database: " + DATABASE_CONFIGURATION.getDbUsername());
    return DriverManager.getConnection(DATABASE_CONFIGURATION.getDbUrl(),
        DATABASE_CONFIGURATION.getDbUsername(), DATABASE_CONFIGURATION.getDbPassword());
  }

}

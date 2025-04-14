package com.nghlong3004.telegrambot.server.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.nghlong3004.telegrambot.server.repository.DatabaseRepository;
import com.nghlong3004.telegrambot.server.utils.ObjectUtilContainer;

public class DatabaseService {
  private static final Logger LOGGER = Logger.getLogger(DatabaseService.class);

  public void insertUserIfNotExists(String fullName, long telegramId) {
    try (Connection connection = ObjectUtilContainer.getDatabaseUtil().getConnection();
        PreparedStatement checkStmt =
            connection.prepareStatement(DatabaseRepository.SELECT_USER_BY_TELEGRAM_ID)) {

      checkStmt.setLong(1, telegramId);
      ResultSet rs = checkStmt.executeQuery();

      if (!rs.next()) {
        try (PreparedStatement insertStmt =
            connection.prepareStatement(DatabaseRepository.INSERT_NEW_USER)) {
          insertStmt.setString(1, fullName);
          insertStmt.setLong(2, telegramId);
          insertStmt.executeUpdate();
          LOGGER.info("Add new user: " + fullName + " (" + telegramId + ")");
        }
      } else {
        LOGGER.info("User has existed: " + telegramId);
      }

    } catch (SQLException e) {
      LOGGER.error("Error checking/adding user " + e.getMessage());
    }
  }

  public void insertUserMessage(long telegramId, String userMessage, String botReply) {
    try (Connection connection = ObjectUtilContainer.getDatabaseUtil().getConnection();
        PreparedStatement stmt = connection.prepareStatement(DatabaseRepository.INSERT_USER_MESSAGE)) {

      stmt.setLong(1, telegramId);
      stmt.setString(2, userMessage);
      stmt.setString(3, botReply);
      stmt.executeUpdate();
      LOGGER.info("Message has been saved for user: " + telegramId);
    } catch (SQLException e) {
      LOGGER.error("Error writing message: " + e.getMessage());
    }
  }

  public void insertServerHistory(String response) {
    try (Connection connection = ObjectUtilContainer.getDatabaseUtil().getConnection();
        PreparedStatement stmt = connection.prepareStatement(DatabaseRepository.INSERT_SERVER_HISTORY)) {
      stmt.setString(1, response);
      stmt.executeUpdate();
      LOGGER.info("Server response has been saved.");
    } catch (SQLException e) {
      LOGGER.error("Error writing server response: " + e.getMessage());
    }
  }

}

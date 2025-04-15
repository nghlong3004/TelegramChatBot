package com.nghlong3004.telegrambot.server.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
          LOGGER.info("[DB] Add new user: " + fullName + " (" + telegramId + ")");
        }
      } else {
        LOGGER.info("[DB] User has existed: " + telegramId);
      }

    } catch (SQLException e) {
      LOGGER.error("[DB] Error checking/adding user " + e.getMessage());
    }
  }

  public void insertUserMessage(long telegramId, String userMessage, String botReply) {
    try (Connection connection = ObjectUtilContainer.getDatabaseUtil().getConnection();
        PreparedStatement stmt =
            connection.prepareStatement(DatabaseRepository.INSERT_USER_MESSAGE)) {
      stmt.setLong(1, telegramId);
      stmt.setString(2, userMessage);
      stmt.setString(3, botReply);
      stmt.executeUpdate();
      LOGGER.info("[DB] Message has been saved for user: " + telegramId);
    } catch (SQLException e) {
      LOGGER.error("[DB] Error writing message: " + e.getMessage());
    }
  }

  public void insertServerHistory(String response, String coinType) {
    try (Connection connection = ObjectUtilContainer.getDatabaseUtil().getConnection();
        PreparedStatement stmt =
            connection.prepareStatement(DatabaseRepository.INSERT_SERVER_HISTORY)) {
      stmt.setString(1, response);
      stmt.setString(2, coinType);
      stmt.executeUpdate();
      LOGGER.info("[DB] Server response has been saved.");
    } catch (SQLException e) {
      LOGGER.error("[DB] Error writing server response: " + e.getMessage());
    }
  }

  public String getTodayServerHistoriesByKeyword(String keyword) {
    StringBuilder result = new StringBuilder();
    try (Connection conn = ObjectUtilContainer.getDatabaseUtil().getConnection();
        PreparedStatement stmt =
            conn.prepareStatement(DatabaseRepository.SELECT_SERVER_HISTORY_BY_KEYWORD)) {
      stmt.setString(1, "%" + keyword + "%");
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        String response = rs.getString("server_response");
        Timestamp timestamp = rs.getTimestamp("timestamp");
        result.append("🕒 ").append(myDateTime(timestamp.toLocalDateTime().toString()))
            .append(" - ").append(response).append("\n");
      }

    } catch (SQLException e) {
      LOGGER.error("[DB] Error fetching today server histories: ", e);
    }

    return result.length() > 0 ? result.toString() : "No records found for today (" + keyword + ")";
  }

  private String myDateTime(String timestamp) {
    String result = "";
    for (int i = 0; i < timestamp.length(); ++i) {
      if (timestamp.charAt(i) == 'T') {
        result += ' ';
      } else if (timestamp.charAt(i) == '.') {
        break;
      } else {
        result += timestamp.charAt(i);
      }
    }
    return result;
  }

}

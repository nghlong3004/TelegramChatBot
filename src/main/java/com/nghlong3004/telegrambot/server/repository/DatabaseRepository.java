package com.nghlong3004.telegrambot.server.repository;

public class DatabaseRepository {

  public static final String SELECT_USER_BY_TELEGRAM_ID =
      "SELECT * FROM telegram.users WHERE telegram_id = ?";

  public static final String INSERT_NEW_USER =
      "INSERT INTO telegram.users (full_name, telegram_id) VALUES (?, ?)";

  public static final String INSERT_USER_MESSAGE =
      "INSERT INTO telegram.user_messages (user_id, user_message, bot_reply) VALUES (?, ?, ?)";

  public static final String INSERT_SERVER_HISTORY =
      "INSERT INTO telegram.server_history (server_response, coin_type) VALUES (?, ?)";
  public static final String SELECT_SERVER_HISTORY_BY_KEYWORD =
      "SELECT server_response, timestamp FROM telegram.server_history " + "WHERE coin_type ILIKE ? "
          + "AND timestamp >= CURRENT_DATE " + "AND timestamp < CURRENT_DATE + INTERVAL '1 day' "
          + "ORDER BY timestamp DESC";

}

package com.nghlong3004.telegrambot.server.service;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import com.nghlong3004.telegrambot.server.TaskScheduler;
import com.nghlong3004.telegrambot.server.utils.ObjectUtilContainer;

public class TelegramBotService {
  private static final Logger LOGGER = Logger.getLogger(TelegramBotService.class);

  public TelegramBotService() {
    LOGGER.info("[SERVER] TelegramBotService initialized");
  }

  public void connection() {
    try (TelegramBotsLongPollingApplication botsApplication =
        new TelegramBotsLongPollingApplication()) {
      botsApplication.registerBot(ObjectUtilContainer.getPropertyUtil().getTelegramToken(),
          ObjectUtilContainer.getTelegramBotUtil());
      LOGGER.info("[SERVER] Telegram bot successfully started.");
      new TaskScheduler().startAll();
      Thread.currentThread().join();
    } catch (Exception e) {
      LOGGER.error("[SERVER] Error while starting Telegram bot: ", e);
    }
  }

}

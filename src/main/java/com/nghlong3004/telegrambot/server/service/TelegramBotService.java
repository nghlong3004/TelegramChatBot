package com.nghlong3004.telegrambot.server.service;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import com.nghlong3004.telegrambot.server.utils.ObjectUtilContainer;

public class TelegramBotService {
  
  public TelegramBotService() {
    
  }
  
  public void connection() {
    try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
        botsApplication.registerBot(ObjectUtilContainer.getPropertyUtil().getTelegramToken(), ObjectUtilContainer.getTelegramBot());
        System.out.println("BotTest successfully started!");
        Thread.currentThread().join();
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
  
}

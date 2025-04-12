package com.nghlong3004.telegrambot.server.service;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import com.nghlong3004.telegrambot.server.utils.TelegramBotUtil;

public class TelegramBotService {
  
  public TelegramBotService() {
    
  }
  
  public void connection() {
    String botToken = "8040615483:AAFyctmNVMmyatnG9Z07vD28tlIhcEsJd9U";
    try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
        botsApplication.registerBot(botToken, new TelegramBotUtil(botToken));
        System.out.println("BotTest successfully started!");
        Thread.currentThread().join();
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
  
}

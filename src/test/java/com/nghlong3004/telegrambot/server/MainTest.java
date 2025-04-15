package com.nghlong3004.telegrambot.server;

import com.nghlong3004.telegrambot.server.service.TelegramBotService;

public class MainTest {
  public static void main(String[] args) {
    TelegramBotService botService = new TelegramBotService();
    botService.connection();
  }
}

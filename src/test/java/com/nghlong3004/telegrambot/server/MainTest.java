package com.nghlong3004.telegrambot.server;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import com.nghlong3004.telegrambot.server.utils.TelegramBotUtil;

public class MainTest {
  public static void main(String[] args) {
    String botToken = "";
    try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
        botsApplication.registerBot(botToken, new TelegramBotUtil(botToken));
        System.out.println("BotTest successfully started!");
        Thread.currentThread().join();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

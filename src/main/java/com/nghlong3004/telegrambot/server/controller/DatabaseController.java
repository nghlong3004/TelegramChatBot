package com.nghlong3004.telegrambot.server.controller;

import com.nghlong3004.telegrambot.server.service.DatabaseService;

public class DatabaseController {
  
  private static final DatabaseService DATABASE_SERVICE = new DatabaseService();

  public static DatabaseService getDatabaseService() {
    return DATABASE_SERVICE;
  }
  
}

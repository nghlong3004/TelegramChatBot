package com.nghlong3004.telegrambot.server.service;

import com.nghlong3004.telegrambot.server.utils.ObjectUtilContainer;

public class APIService {
  
  public APIService() {
    
  }
  
  public String askOpenAi(String message) {
    return ObjectUtilContainer.getOpenAi().ask(message);
  }
  
}

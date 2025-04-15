package com.nghlong3004.telegrambot.server.task;

import com.nghlong3004.telegrambot.server.utils.ObjectUtilContainer;
import com.nghlong3004.telegrambot.server.controller.DatabaseController;

import org.apache.log4j.Logger;

public class PriceAnalyzerTask implements Runnable {

  private static final Logger LOGGER = Logger.getLogger(PriceAnalyzerTask.class);

  private final String symbol;
  private final String label;

  public PriceAnalyzerTask(String symbol, String label) {
    this.symbol = symbol;
    this.label = label;
  }

  @Override
  public void run() {
    try {
      LOGGER.info("[BOT] Running " + label + " analyzer task...");
      String prompt = ObjectUtilContainer.getApiBinanceUtil().getPricePrompt(symbol,
          "Current " + label + " price is %s USDT.");
      if (prompt.startsWith("Unable to fetch")) {
        LOGGER.warn("[SERVER] Skipping " + label + " analysis due to missing price.");
        return;
      }
      String aiReply = ObjectUtilContainer.getApiOpenAiUtil().ask(prompt);
      LOGGER.info("[BOT] " + label + " analysis complete.");
      DatabaseController.getDatabaseService().insertServerHistory(aiReply, label);
    } catch (Exception e) {
      LOGGER.error("[SERVER] Exception in " + label + "AnalyzerTask:", e);
    }
  }
}

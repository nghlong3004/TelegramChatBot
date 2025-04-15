package com.nghlong3004.telegrambot.server;

import org.apache.log4j.Logger;
import com.nghlong3004.telegrambot.server.task.PriceAnalyzerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {

  private static final Logger LOGGER = Logger.getLogger(TaskScheduler.class);
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

  public void startAll() {
    LOGGER.info("[SERVER] Starting background analysis tasks...");
    scheduler.scheduleAtFixedRate(new PriceAnalyzerTask("BTCUSDT", "BTC"), 0, 3, TimeUnit.MINUTES);
    scheduler.scheduleAtFixedRate(new PriceAnalyzerTask("ETHUSDT", "ETH"), 0, 3, TimeUnit.MINUTES);
  }
}

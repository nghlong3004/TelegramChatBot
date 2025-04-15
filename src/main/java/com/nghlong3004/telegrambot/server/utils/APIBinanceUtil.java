package com.nghlong3004.telegrambot.server.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class APIBinanceUtil {
  private static final Logger LOGGER = Logger.getLogger(APIBinanceUtil.class);
  private final OkHttpClient client;

  public APIBinanceUtil() {
    this.client = new OkHttpClient();
  }

  public String getPricePrompt(String symbol, String promptFormat) {
    Request request = new Request.Builder().url(ConstantsUtil.URL_BINANCE + symbol).build();
    try (Response response = client.newCall(request).execute()) {
      if (response.isSuccessful() && response.body() != null) {
        String body = response.body().string();
        JSONObject json = new JSONObject(body);
        String price = json.getString("price");
        LOGGER.info("[BINANCE] " + symbol + " price: " + price);
        return String.format(promptFormat
            + "\n\nPlease analyze based on the elliott wave and answer using this format:\n\nPrice: <price> USDT\nAction: <Buy/Sell/Neutral>\nTake Profit: balance\nStop Lot: balance\nReason: <brief reason>\n.",
            price);
      } else {
        LOGGER
            .warn("[BINANCE] Failed to fetch " + symbol + " price. HTTP code: " + response.code());
        return "Unable to fetch " + symbol + " price from Binance.";
      }
    } catch (Exception e) {
      LOGGER.error("[BINANCE] Exception while calling API: ", e);
      return "Unable to fetch " + symbol + " price from Binance.";
    }
  }
}

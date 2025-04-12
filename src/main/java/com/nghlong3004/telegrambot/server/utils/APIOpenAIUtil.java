package com.nghlong3004.telegrambot.server.utils;

import java.util.Arrays;
import java.util.List;
import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestSystemMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.AzureKeyCredential;

public class APIOpenAIUtil {
  
  private String apikey;
  
  public APIOpenAIUtil(String apikey) {
    this.apikey = apikey;
  }
  
  public String ask(String message) {
    ChatCompletionsClient client = new ChatCompletionsClientBuilder()
        .credential(new AzureKeyCredential(apikey)).endpoint(ConstantsUtil.ENDPOINT).buildClient();

    List<ChatRequestMessage> chatMessages = Arrays.asList(new ChatRequestSystemMessage(""),
        new ChatRequestUserMessage(message));

    ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
    chatCompletionsOptions.setModel(ConstantsUtil.MODEL);

    ChatCompletions completions = client.complete(chatCompletionsOptions);

    return completions.getChoice().getMessage().getContent();
  }
  
}

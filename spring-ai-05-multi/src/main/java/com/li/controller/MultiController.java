package com.li.controller;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 多模态控制层
 * @author: lh
 * @create: 2025-01-17 13:50
 **/
@RestController
public class MultiController {
    @Autowired
    private OpenAiChatModel openAiChatModel;

    @RequestMapping("/ai/moderation")
    public String moderation() {
        var imageData = new ClassPathResource("multimodal.png");
        var userMessage = new UserMessage("Explain what do you see on this picture?",
                List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageData)));
        var response = openAiChatModel
                .call(new Prompt(List.of(userMessage), OpenAiChatOptions.builder().withModel(OpenAiApi.ChatModel.GPT_4_O.getValue()).build()));
        System.out.println(response.getResult().getOutput().getContent());

        return "ok";
    }

}

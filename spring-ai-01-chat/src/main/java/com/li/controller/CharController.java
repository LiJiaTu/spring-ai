package com.li.controller;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @description: 控制层
 * @author: lh
 * @create: 2025-01-16 14:19
 **/
@RestController
public class CharController {

    @Autowired
    private OpenAiChatModel openAiChatModel;

    /**
     * 调用 Open-ai 聊天接口
     *
     * @param prompt 问题文本
     * @return 回答文本
     */
    @RequestMapping("/ai/chat")
    public String chat(@RequestParam(value = "prompt") String prompt) {
        return openAiChatModel.call(prompt);
    }

    /**
     * 调用 Open-ai 聊天接口
     *
     * @param prompt 问题文本 + 配置参数
     * @return 回答文本
     */
    @RequestMapping("/ai/chat2")
    public Object chat2(@RequestParam(value = "prompt") String prompt) {
        ChatResponse chatResponse = openAiChatModel.call(new Prompt(prompt, OpenAiChatOptions.builder()
                .withModel("gpt-4o")
                .withTemperature(0.4)
                .build()));
        return chatResponse;
    }

    /**
     * 调用 Open-ai 聊天接口
     *
     * @param prompt 问题文本
     * @return 回答文本 - 流式返回
     */
    @RequestMapping("/ai/chat3")
    public Flux<String> chat3(@RequestParam(value = "prompt") String prompt) {
        Flux<String> flux = openAiChatModel.stream(prompt);
        return flux;
    }
}

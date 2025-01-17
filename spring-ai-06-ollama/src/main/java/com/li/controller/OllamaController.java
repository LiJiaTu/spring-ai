package com.li.controller;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: ollama控制层
 * @author: lh
 * @create: 2025-01-17 16:21
 **/
@RestController
public class OllamaController {

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @RequestMapping("/ai/ollama")
    public Object chat(@RequestParam(value = "prompt") String prompt) {
        return ollamaChatModel.call(prompt);
    }
}

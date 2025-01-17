package com.li.controller;

import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 音频翻译控制层
 * @author: lh
 * @create: 2025-01-17 10:36
 **/
@RestController
public class AudioController {

    @Autowired
    private OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;


    @RequestMapping("/ai/audio")
    public void translate() {
        ClassPathResource audioResource = new ClassPathResource("赵英俊 - 送你一朵小红花.mp3");
        System.out.println(openAiAudioTranscriptionModel.call(audioResource));
    }
}

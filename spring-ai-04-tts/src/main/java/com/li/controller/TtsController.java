package com.li.controller;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @description: 音频转文本Controller
 * @author: lh
 * @create: 2025-01-17 11:04
 **/
@RestController
public class TtsController {

    @Autowired
    private OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @RequestMapping("/ai/tts")
    public String tts() {
        String text = "哈喽哈喽，你好李淑英，我是你男朋友的智能工作助手，没错就是像钢铁侠的贾维斯一样，我今年一岁了，我最喜欢的颜色是绿色，很高兴认识你！";
        byte[] call = openAiAudioSpeechModel.call(text);
        // 把返回的byte数组call转成mp3
        saveByteArrayToMp3(call, "D:\\test\\tts.mp3");
        return "ok";
    }

    /**
     * 将 byte[] 数组写入到 MP3 文件
     *
     * @param audioBytes 音频字节数组
     * @param filePath   输出的 MP3 文件路径
     */
    public void saveByteArrayToMp3(byte[] audioBytes, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(audioBytes);
            System.out.println("MP3 文件已成功保存到: " + filePath);
        } catch (IOException e) {
            System.err.println("保存 MP3 文件时发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

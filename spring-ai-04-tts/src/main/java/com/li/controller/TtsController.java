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
        String text = "送你一朵小紅花 送你一朵小紅花 開在你左天心上的枝桠 獎勵你有勇氣 主動來和我說話 不共戴天的冰水啊 義無反顧的烈酒啊 多麼苦難的日子裡 你都已戰勝了它 送你一朵小紅花 遮住你今天心田的傷疤 獎勵你在下雨天 還願意送我回家 科羅拉多的風雪啊 喜瑪拉雅的驟雨啊 只要你相信我 閉上眼就能到達 送你一朵小紅花 開在那牛羊遍野的田呀 想離你走到哪兒 都不會忘記我啊 潔白如雪的沙灘啊 風平浪靜的湖水啊 那些城市都歡迎啊 是我給你的牽掛 送給你一朵小紅花 開在你心裡最深的泥沼 想離你能感受 每個明年的征兆 是誰揮霍的時光啊 是誰苦苦的奢望啊 這不是一個問題 也不需要一等回答 送你一朵小紅花 送你一朵小紅花 送你一朵小紅花 送你一朵小紅花 送你一朵小紅花\n";
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

package com.li.controller;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 图像控制层
 * @author: lh
 * @create: 2025-01-17 09:11
 **/
@RestController
public class ImageController {

    @Autowired
    private OpenAiImageModel openAiImageModel;

    /**
     * 创建图像 - 这个1.0之后版本不能用了，需要指定图片的大小，否则会报错
     *
     * @param prompt 创建图片提示词
     * @return 图片地址
     */
    @RequestMapping("/ai/image")
    public Object createImage(@RequestParam(value = "prompt") String prompt) {
        return openAiImageModel.call(new ImagePrompt(prompt)).getResult().getOutput();
    }

    /**
     * 创建图像 - 指定生成图片参数
     *
     * @param prompt 创建图片提示词
     * @return 图片地址
     */
    @RequestMapping("/ai/image1")
    public Object createImage1(@RequestParam(value = "prompt") String prompt) {
        ImagePrompt imagePrompt = new ImagePrompt(prompt, OpenAiImageOptions.builder()
                .withQuality("hd")
                .withN(1)
                .withHeight(1024)
                .withWidth(1024)
                .build());
        return openAiImageModel.call(imagePrompt).getResult().getOutput().getUrl();
    }
}

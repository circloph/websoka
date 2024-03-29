package com.kruglov.websoka.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename().split("\\.")[0] + "-uploaded.png")));
                stream.write(bytes);
                stream.close();
                return "Вы удачно загрузили " + "name" + " в " + "name" + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + "name" + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + "name" + " потому что файл пустой.";
        }
    }
    
}

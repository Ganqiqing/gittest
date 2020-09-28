package com.gan.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/more")
public class MoreUploadController {
    @RequestMapping(value = "/index")
    public String moreUpload() {
        return "moreUpload";
    }

    @RequestMapping(value = "/ajax")
    public String ajax() {
        return "ajaxMore";
    }

    /*
     *
     * 多文件上传*/
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(MultipartFile[] files, HttpServletRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd");
        String format = simpleDateFormat.format(new Date());
        String path = request.getServletContext().getRealPath("/image") + format;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (MultipartFile multipartFile : files) {
            String oldName = multipartFile.getOriginalFilename();
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
            try {
                multipartFile.transferTo(new File(folder, newName));
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        return "success";
    }

}

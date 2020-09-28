package com.gan.demo.controller;

import com.gan.demo.pojo.Product;
import com.gan.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/redis")
public class ProductRedisController {
    @Autowired
    private ProductService productService;

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");

    @RequestMapping(value = "/test")
    @ResponseBody
    public List<Product> productList() {
        return productService.searchProduct();
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/ajaxIndex")
    public String ajax() {
        return "ajaxIndex";
    }

    /*在文件上传的时候，控制层接受前端传来的内容，用MultipartFile来处理
     * HttpServletRequest：用来定义文件上传到服务器的路径*/
    @RequestMapping("/upload")
    @ResponseBody                    //file和html里的<input type="file" name="file"/>同名
    public String upload(MultipartFile file, HttpServletRequest request) {
        //1.定义文件上传的目录，定义文件在服务器的存放路径
        String format = sdf.format(new Date());
        String path = request.getServletContext().getRealPath("/img") + format;
        //2.判断文件夹是否存在，不存在创建
        File foler = new File(path);//创建文件（虚拟）
        if (!foler.exists()) { //判断文件是否存在
            foler.mkdirs();//boolean mkdirs: 创建多级文件夹
        }
        //3.接受文件信息
        String oldName = file.getOriginalFilename();//获得上传文件的名称
        //生成一个新的文件名+后缀名                              获取原始文件的后缀名
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            /*4.上传  transferTo
             * new File(foler,newName)    父目录绝对路径+子目录名称   */
            file.transferTo(new File(foler, newName));
            String url = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + "/img" + format + newName;
            return url;
            //return "success";
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "error";
    }

}

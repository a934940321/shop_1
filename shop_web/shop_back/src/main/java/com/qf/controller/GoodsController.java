package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("goods")
public class GoodsController {

    @Reference
    private IGoodsService goodsService;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @RequestMapping("toList")
    public String toList(Model model){
        List<Goods> goods = goodsService.toList();
        model.addAttribute("goods",goods);
        System.err.println("goods---"+goods);
        return "/goodslist";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "/addgoods";
    }


    //获取配置文件中配置的文件保存地址
//    @Value("${upload.path}")
//    private String uploadPath;

    //原始图片上传
  /*  @RequestMapping("uploadImg")
    @ResponseBody
    public String uploadImg(MultipartFile file){
        String uploadFile = "";
        //拿到文件第一步替换成唯一命名
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        //获得文件后缀
        String suffix = originalFilename.substring(index);
        //生成唯一文件名
        String fileName = UUID.randomUUID().toString() + suffix;
        //设置上传文件路径
        uploadFile = uploadPath + fileName;
        try (
                //获取文件的输入流
                InputStream inputStream = file.getInputStream();
                //创建输出流
                OutputStream outputStream = new FileOutputStream(uploadFile);
                ){
            //使用io工具类实现文件拷贝
            IOUtils.copy(inputStream,outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "{\"filepath\":\"" + uploadFile + "\"}";
        return uploadFile;
    }*/

//fastDFS 图片上传中心
    @RequestMapping("uploadImg")
    @ResponseBody
    public String uploadImg(MultipartFile file){
        String uploadFile = "";
        //拿到文件第一步替换成唯一命名
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        //获得文件后缀
        String suffix = originalFilename.substring(index);

        try {
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
                    file.getInputStream(),
                    file.getSize(),
                    "jpg",
                    null
            );
            System.out.println("上传的路径为："+storePath.getFullPath());
            uploadFile = storePath.getFullPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "{\"filepath\":\"" + uploadFile + "\"}";
            return uploadFile;
    }


    @RequestMapping("addGoods")
    public String addGoods(Goods goods){
        int i = goodsService.addGoods(goods);
        return "redirect:/goods/toList";
    }

//原始获取图片
   /* @RequestMapping("getImg")
    public void getImg(String imgpath, HttpServletResponse response) {
        System.err.println(imgpath);
        //获取本地图片
        File file = new File(imgpath);

        try (
                InputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = response.getOutputStream();
        ) {
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    @RequestMapping("up")
    @ResponseBody
    public String up(MultipartFile upload_img){

        String uploadFile = "";
        //拿到文件第一步替换成唯一命名
        String originalFilename = upload_img.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        //获得文件后缀
        String suffix = originalFilename.substring(index);
        try {
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
                    upload_img.getInputStream(),
                    upload_img.getSize(),
                    "jpg",
                    null
            );

            uploadFile = storePath.getFullPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = "{\"error\" : 0, \"url\" : \"http://192.168.79.188:8080/"+uploadFile+"\"}";
        System.out.println(s);
        return s;
    }


}

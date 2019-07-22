package com.qf.shop_back;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.entity.BackUser;
import com.qf.service.IBuserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.nio.cs.FastCharsetProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopBackApplicationTests {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Reference
    private IBuserService buserService;
    @Test
    public void contextLoads() {
        BackUser backUser = buserService.checkBuser("admin");
        System.out.println(backUser);
    }

    //图片上传测试
    @Test
    public void upload(){
        System.out.println("图片开始上传");
        File file = new File("D:\\img\\mi8.jpg");
        try {
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
                    new FileInputStream(file),
                    file.length(),
                    "jpg",
                    null
            );
            System.out.println("上传的路径为："+storePath.getFullPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

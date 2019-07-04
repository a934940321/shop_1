package com.qf.shop_back;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.BackUser;
import com.qf.service.IBuserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopBackApplicationTests {

    @Reference
    private IBuserService buserService;
    @Test
    public void contextLoads() {
        BackUser backUser = buserService.checkBuser("admin");
        System.out.println(backUser);
    }

}
